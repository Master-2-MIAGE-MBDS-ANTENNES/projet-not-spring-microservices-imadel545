package fr.miage.mbds.userservice.service;

import fr.miage.mbds.userservice.dto.UserDTO;
import fr.miage.mbds.userservice.dto.UserResponseDTO;
import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAll();
    UserResponseDTO getById(Long id);
    UserResponseDTO create(UserDTO dto);
    void delete(Long id);
}
