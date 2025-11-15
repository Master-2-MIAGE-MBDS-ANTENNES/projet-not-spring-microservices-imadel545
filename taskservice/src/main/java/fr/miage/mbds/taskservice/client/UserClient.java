package fr.miage.mbds.taskservice.client;

import fr.miage.mbds.taskservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "userservice", url = "${userservice.base-url}")
public interface UserClient {

    @GetMapping("/api/users")
    List<UserResponseDTO> getAllUsers();

    @GetMapping("/api/users/{id}")
    UserResponseDTO getUserById(@PathVariable("id") Long id);
}
