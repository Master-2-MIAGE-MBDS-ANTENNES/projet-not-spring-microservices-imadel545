package fr.miage.mbds.userservice.mapper;

import fr.miage.mbds.userservice.dto.UserDTO;
import fr.miage.mbds.userservice.dto.UserResponseDTO;
import fr.miage.mbds.userservice.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return new User(dto.getNom(), dto.getPrenom(), dto.getEmail());
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail()
        );
    }
}
