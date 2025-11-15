package fr.miage.mbds.taskservice.repository;

import fr.miage.mbds.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
