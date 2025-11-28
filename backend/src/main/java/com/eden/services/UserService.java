package com.eden.services;

import com.eden.dto.user.*;
import com.eden.models.UserModel;
import com.eden.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserResponseDTO(
                        u.getId(),
                        u.getName(),
                        u.getEmail()))
                .toList();
    }

    public UserPostDTO create(UserRequestDTO dto) {
        UserModel user = new UserModel();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setAddress(dto.address());
        user.setPhone(dto.phone());
        user.setCity(dto.city());
        user.setState(dto.state());
        user.setZip(dto.zip());
        user.setCountry(dto.country());
        user.setRole(dto.role());

        userRepository.save(user);

        return new UserPostDTO(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserResponseDTO update(Integer id, UserUpdateDTO userUpdateDTO) {

        UserModel user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));

        user.setName(userUpdateDTO.name());
        user.setEmail(userUpdateDTO.email());

        userRepository.save(user);
        return new UserResponseDTO(user.getId() ,user.getName(), user.getEmail());

    }

    public void updatePassword(Integer id, UserChangePasswordDTO userChangePasswordDTO){
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (userChangePasswordDTO != null &&
                userChangePasswordDTO.oldPassword() != null &&
                userChangePasswordDTO.newPassword() != null) {

            if (!passwordEncoder.matches(userChangePasswordDTO.oldPassword(), user.getPassword())) {
                throw new RuntimeException("Old password does not match!");
            }

            user.setPassword(passwordEncoder.encode(userChangePasswordDTO.newPassword()));
        }
    }

    public UserDeleteDTO delete(Integer id) {

        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return new UserDeleteDTO(user.getId());
    }



}
