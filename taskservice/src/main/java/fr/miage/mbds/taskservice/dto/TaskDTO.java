package fr.miage.mbds.taskservice.dto;

import fr.miage.mbds.taskservice.entity.TaskStatus;

import java.util.List;

public class TaskDTO {

    private String titre;
    private String description;
    private TaskStatus status;
    private List<Long> userIds;

    public TaskDTO() {
    }

    public TaskDTO(String titre, String description, TaskStatus status, List<Long> userIds) {
        this.titre = titre;
        this.description = description;
        this.status = status;
        this.userIds = userIds;
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
