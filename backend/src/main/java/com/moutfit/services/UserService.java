package com.moutfit.services;

import com.moutfit.dto.user.UserRequestDTO;
import com.moutfit.dto.user.UserResponseDTO;
import com.moutfit.models.User;
import com.moutfit.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public void create(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));

        userRepository.save(user);
    }

    public UserResponseDTO update(Integer id, UserRequestDTO userRequestDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));

        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));

        userRepository.save(user);

        return new UserResponseDTO(user.getId() ,user.getName(), user.getEmail());

    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }



}
