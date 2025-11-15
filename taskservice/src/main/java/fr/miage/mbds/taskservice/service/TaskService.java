package fr.miage.mbds.taskservice.service;

import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    List<TaskResponseDTO> getAll();

    TaskResponseDTO getById(Long id);

    TaskResponseDTO create(TaskDTO dto);

    void delete(Long id);
}
