package fr.miage.mbds.taskservice.dto;

import fr.miage.mbds.taskservice.entity.TaskStatus;

public class TaskDTO {

    private String titre;
    private String description;
    private TaskStatus status;

    public TaskDTO() {
    }

    public TaskDTO(String titre, String description, TaskStatus status) {
        this.titre = titre;
        this.description = description;
        this.status = status;
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
}
