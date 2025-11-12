package com.moutfit.controllers;

import com.moutfit.dto.user.UserRequestDTO;
import com.moutfit.dto.user.UserResponseDTO;
import com.moutfit.models.User;
import com.moutfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.moutfit.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updatedUser = userService.update(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
