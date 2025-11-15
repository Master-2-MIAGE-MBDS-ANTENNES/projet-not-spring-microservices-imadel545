package fr.miage.mbds.taskservice.client;

import fr.miage.mbds.taskservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "USERSERVICE"  // nom d’enregistrement dans Eureka
)
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserResponseDTO getUserById(@PathVariable("id") Long id);
}