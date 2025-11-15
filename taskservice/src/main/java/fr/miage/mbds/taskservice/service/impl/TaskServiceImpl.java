package fr.miage.mbds.taskservice.service.impl;

import fr.miage.mbds.taskservice.client.UserClient;
import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;
import fr.miage.mbds.taskservice.dto.UserResponseDTO;
import fr.miage.mbds.taskservice.entity.Task;
import fr.miage.mbds.taskservice.mapper.TaskMapper;
import fr.miage.mbds.taskservice.repository.TaskRepository;
import fr.miage.mbds.taskservice.service.TaskService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserClient userClient;

    // ----------------------
    // Implémentation TaskService
    // ----------------------

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(task -> {
                    TaskResponseDTO dto = taskMapper.toResponseDTO(task);
                    // 🔥 Enrichissement avec les utilisateurs via USERSERVICE
                    enrichWithUsers(task, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDTO getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));

        TaskResponseDTO response = taskMapper.toResponseDTO(task);
        enrichWithUsers(task, response);
        return response;
    }

    @Override
    public TaskResponseDTO create(TaskDTO dto) {
        // 1) Validation fonctionnelle via USERSERVICE (protégée par Resilience4J)
        validateUsers(dto.getUserIds());

        // 2) Mapping et persistance
        Task task = taskMapper.toEntity(dto);
        Task saved = taskRepository.save(task);

        // 3) Construction de la réponse + enrichissement avec les users
        TaskResponseDTO response = taskMapper.toResponseDTO(saved);
        enrichWithUsers(saved, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    // ----------------------
    // Enrichissement avec les utilisateurs (USERSERVICE)
    // ----------------------

    private void enrichWithUsers(Task task, TaskResponseDTO response) {
        List<Long> userIds = task.getUserIds();
        if (userIds == null || userIds.isEmpty()) {
            return;
        }

        List<UserResponseDTO> users = fetchUsersWithCircuitBreaker(userIds);
        response.setUsers(users);
    }

    /**
     * Appel à USERSERVICE protégé par CircuitBreaker + Retry.
     */
    @CircuitBreaker(name = "userService", fallbackMethod = "fetchUsersFallback")
    @Retry(name = "userService")
    public List<UserResponseDTO> fetchUsersWithCircuitBreaker(List<Long> userIds) {
        log.info("Appel USERSERVICE pour récupérer les utilisateurs {}", userIds);
        return userIds.stream()
                .map(userClient::getUserById)
                .collect(Collectors.toList());
    }

    /**
     * Fallback si USERSERVICE est indisponible.
     */
    public List<UserResponseDTO> fetchUsersFallback(List<Long> userIds, Throwable throwable) {
        log.warn("Fallback USERSERVICE: impossible de récupérer les utilisateurs {}. Cause={}",
                userIds, throwable.toString());
        // Décision métier : on renvoie une liste vide pour ne pas casser le service
        return Collections.emptyList();
    }

    /**
     * Validation des userIds avant création de la tâche.
     */
    @CircuitBreaker(name = "userService", fallbackMethod = "validateUsersFallback")
    @Retry(name = "userService")
    public void validateUsers(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return;
        }

        log.info("Validation des userIds {} via USERSERVICE", userIds);
        userIds.forEach(userClient::getUserById);
    }

    /**
     * Fallback de validation : on loggue, mais on n’empêche pas la création.
     */
    public void validateUsersFallback(List<Long> userIds, Throwable throwable) {
        log.warn("Fallback validation users {}: USERSERVICE indisponible, on autorise la création de la tâche. Cause={}",
                userIds, throwable.toString());
    }
}