package fr.miage.mbds.taskservice.mapper;

import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;
import fr.miage.mbds.taskservice.entity.Task;

public class TaskMapper {

    public static Task toEntity(TaskDTO dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setTitre(dto.getTitre());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }

    public static TaskResponseDTO toResponse(Task entity) {
        if (entity == null) {
            return null;
        }
        return new TaskResponseDTO(
                entity.getId(),
                entity.getTitre(),
                entity.getDescription(),
                entity.getStatus()
        );
    }
}
