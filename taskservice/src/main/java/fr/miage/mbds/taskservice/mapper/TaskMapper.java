package fr.miage.mbds.taskservice.mapper;

import fr.miage.mbds.taskservice.dto.TaskDTO;
import fr.miage.mbds.taskservice.dto.TaskResponseDTO;
import fr.miage.mbds.taskservice.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static Task toEntity(TaskDTO dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setTitre(dto.getTitre());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());

        if (dto.getUserIds() != null) {
            task.setUserIds(new ArrayList<>(dto.getUserIds()));
        }

        return task;
    }

    public static TaskResponseDTO toResponse(Task task) {
        if (task == null) {
            return null;
        }

        List<Long> userIdsCopy = null;
        if (task.getUserIds() != null) {
            userIdsCopy = new ArrayList<>(task.getUserIds());
        }

        return new TaskResponseDTO(
                task.getId(),
                task.getTitre(),
                task.getDescription(),
                task.getStatus(),
                userIdsCopy
        );
    }
}
