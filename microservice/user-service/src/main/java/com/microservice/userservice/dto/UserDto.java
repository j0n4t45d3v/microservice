package com.microservice.userservice.dto;

public record UserDto(
        String username,
        String email,
        String password
) {
}
