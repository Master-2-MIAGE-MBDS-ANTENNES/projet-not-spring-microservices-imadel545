package fr.miage.mbds.taskservice.service;

import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    /**
     * Récupérer toutes les tâches.
     */
    List<TaskResponseDTO> getAll();

    /**
     * Récupérer une tâche par son id.
     */
    TaskResponseDTO getById(Long id);

    /**
     * Créer une nouvelle tâche à partir d’un DTO.
     */
    TaskResponseDTO create(TaskDTO dto);

    /**
     * Supprimer une tâche par son id.
     */
    void delete(Long id);
}
