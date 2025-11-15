package fr.miage.mbds.taskservice.dto;

import fr.miage.mbds.taskservice.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {

    private Long id;
    private String titre;
    private String description;
    private TaskStatus status;

    private List<Long> userIds;

    private List<UserResponseDTO> users;
}