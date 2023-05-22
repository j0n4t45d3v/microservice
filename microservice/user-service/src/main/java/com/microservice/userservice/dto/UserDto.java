package com.microservice.userservice.dto;

import com.microservice.userservice.model.User;

public record UserDto(
        String username,
        String email,
        String password
) {

    public static UserDto transformUserToUserDto(User user) {
        return new UserDto(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
