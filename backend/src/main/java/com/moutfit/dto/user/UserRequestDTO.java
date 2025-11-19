package com.moutfit.dto.user;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        String address,
        String phone,
        String city,
        String state,
        String zip,
        String country,
        String role
) {}

