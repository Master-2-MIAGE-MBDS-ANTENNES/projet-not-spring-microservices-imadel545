package fr.miage.mbds.taskservice.dto;

import fr.miage.mbds.taskservice.entity.TaskStatus;

import java.util.List;

public class TaskResponseDTO {

    private Long id;
    private String titre;
    private String description;
    private TaskStatus status;
    private List<Long> userIds;

    public TaskResponseDTO() {
    }

    public TaskResponseDTO(Long id, String titre, String description, TaskStatus status, List<Long> userIds) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.status = status;
        this.userIds = userIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
