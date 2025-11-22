package com.eden.controllers;

import com.eden.dto.user.UserChangePasswordDTO;
import com.eden.dto.user.UserRequestDTO;
import com.eden.dto.user.UserResponseDTO;
import com.eden.dto.user.UserUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import com.eden.services.UserService;

import java.util.List;

@EnableMethodSecurity
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserResponseDTO updatedUser = userService.update(id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id, @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        userService.updatePassword(id, userChangePasswordDTO);
        return ResponseEntity.ok("Password updated successfully");
    }

}
