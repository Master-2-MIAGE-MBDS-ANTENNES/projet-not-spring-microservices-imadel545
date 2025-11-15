package fr.miage.mbds.userservice.repository;

import fr.miage.mbds.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
