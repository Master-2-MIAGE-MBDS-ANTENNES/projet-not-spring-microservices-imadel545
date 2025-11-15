package fr.miage.mbds.userservice.service.impl;

import fr.miage.mbds.userservice.dto.UserDTO;
import fr.miage.mbds.userservice.dto.UserResponseDTO;
import fr.miage.mbds.userservice.entity.User;
import fr.miage.mbds.userservice.mapper.UserMapper;
import fr.miage.mbds.userservice.repository.UserRepository;
import fr.miage.mbds.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(UserMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public UserResponseDTO create(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toResponseDTO(repository.save(user));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
