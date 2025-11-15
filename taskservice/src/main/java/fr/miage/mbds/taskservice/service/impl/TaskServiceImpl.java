package fr.miage.mbds.taskservice.service.impl;

import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;
import fr.miage.mbds.taskservice.entity.Task;
import fr.miage.mbds.taskservice.mapper.TaskMapper;
import fr.miage.mbds.taskservice.repository.TaskRepository;
import fr.miage.mbds.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TaskResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getById(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        return TaskMapper.toResponse(task);
    }

    @Override
    public TaskResponseDTO create(TaskDTO dto) {
        Task task = TaskMapper.toEntity(dto);
        Task saved = repository.save(task);
        return TaskMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        repository.deleteById(id);
    }
}
