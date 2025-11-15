package fr.miage.mbds.taskservice.mapper;

import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;
import fr.miage.mbds.taskservice.entity.Task;
import fr.miage.mbds.taskservice.entity.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskDTO dto) {
        if (dto == null) {
            return null;
        }

        Task task = new Task();
        task.setTitre(dto.getTitre());
        task.setDescription(dto.getDescription());
        task.setStatus(TaskStatus.OUVERTE);
        task.setUserIds(dto.getUserIds());
        return task;
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        if (task == null) {
            return null;
        }

        return TaskResponseDTO.builder()
                .id(task.getId())
                .titre(task.getTitre())
                .description(task.getDescription())
                .status(task.getStatus())
                .userIds(task.getUserIds())
                .build();
    }
}