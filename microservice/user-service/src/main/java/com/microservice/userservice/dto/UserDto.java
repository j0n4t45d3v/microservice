package com.microservice.userservice.dto;

import com.microservice.userservice.model.User;

public record UserDto(
        String username,
        String email,
        String password
) {
    public UserDto(User user){
        this(user.getUsername(), user.getEmail(), user.getPassword());
    }


    public User ToUser() {
        return User.builder()
                .username(this.username())
                .email(this.email())
                .password(this.password())
                .build();
    }
}
