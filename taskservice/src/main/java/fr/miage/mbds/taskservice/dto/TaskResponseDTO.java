package fr.miage.mbds.taskservice.dto;

import fr.miage.mbds.taskservice.entity.TaskStatus;

public class TaskResponseDTO {

    private Long id;
    private String titre;
    private String description;
    private TaskStatus status;

    public TaskResponseDTO() {
    }

    public TaskResponseDTO(Long id, String titre, String description, TaskStatus status) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
