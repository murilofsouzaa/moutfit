package com.moutfit.services;

import com.moutfit.dto.user.UserChangePasswordDTO;
import com.moutfit.dto.user.UserRequestDTO;
import com.moutfit.dto.user.UserResponseDTO;
import com.moutfit.dto.user.UserUpdateDTO;
import com.moutfit.models.User;
import com.moutfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserResponseDTO(
                        u.getId(),
                        u.getName(),
                        u.getEmail()))
                .toList();
    }

    public UserResponseDTO create(UserRequestDTO dto) {
        User user = new User();

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

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


    public UserResponseDTO update(Integer id, UserUpdateDTO userUpdateDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));

        user.setName(userUpdateDTO.name());
        user.setEmail(userUpdateDTO.email());

        userRepository.save(user);
        return new UserResponseDTO(user.getId() ,user.getName(), user.getEmail());

    }

    public void updatePassword(Integer id, UserChangePasswordDTO userChangePasswordDTO){
        User user = userRepository.findById(id)
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

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }



}
