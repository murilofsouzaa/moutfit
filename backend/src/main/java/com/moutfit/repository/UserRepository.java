package com.moutfit.repository;

import com.moutfit.dto.user.UserResponseDTO;
import com.moutfit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
