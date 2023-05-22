package com.microservice.userservice.service;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.model.User;
import com.microservice.userservice.repository.UserRepository;
import io.javatab.microservices.util.MapperCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        return this.userRepository.findAll()
                .stream().map(this::transformUserToUserDto)
                .toList();
    }

    @Override
    public UserDto getById(Long id) {
        return this.userRepository.findById(id)
                .map(this::transformUserToUserDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "usuario não encontrado"
                ));
    }

    @Override
    public void create(UserDto user) {
        this.userRepository.findByEmail(user.email())
                .ifPresent((u) -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "email " + u.getEmail() + " já é cadastrado"
                    );
                });

        User newUser = User.builder()
                .username(user.username())
                .email(user.email())
                .password(user.password())
                .build();

        this.userRepository.save(newUser);
    }

    @Override
    public void update(Long id, UserDto userDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "usuario não encontrado"
                ));

        this.userRepository.findByEmail(userDto.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "email já cadastrado"
                ));

        this.copyNotNullProperties(userDto, user);

        this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "usuario não encontrado"
                ));

        this.userRepository.deleteById(id);
    }

    private void copyNotNullProperties(UserDto dto, User user) {
        MapperCustom.updatePropererIfNotNull(user::setUsername, dto.username());
        MapperCustom.updatePropererIfNotNull(user::setEmail, dto.email());
        MapperCustom.updatePropererIfNotNull(user::setPassword, dto.password());
    }

    private UserDto transformUserToUserDto(User user) {
        return new UserDto(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
