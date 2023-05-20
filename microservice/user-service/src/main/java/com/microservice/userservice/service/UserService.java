package com.microservice.userservice.service;

import com.microservice.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    void create(UserDto user);

    void update(Long id, UserDto user);

    void delete(Long id);
}
