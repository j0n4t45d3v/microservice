package com.microservice.userservice.service;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.model.User;
import com.microservice.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UserDto> getAll() {
        return this.userRepository.findAllParserDto();
    }

    @Override
    public UserDto getById(Long id) {
        return this.userAlreadyExists(id);
    }

    @Override
    public void create(UserDto user) {
        this.userAlreadyExists(user.email());

        User newUser = user.ToUser();

        this.userRepository.save(newUser);
    }

    @Override
    public void update(Long id, UserDto userDto) {
        User user = this.userAlreadyExists(id).ToUser();

        this.userAlreadyExists(userDto.email());

        this.copyProperties(userDto, user);

        this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        this.userAlreadyExists(id);
        this.userRepository.deleteById(id);
    }

    private void copyProperties(UserDto dto, User user) {
        mapper.map(dto.ToUser(), user);
    }

    private void userAlreadyExists(String email) {
        this.userRepository.findByEmail(email)
                .ifPresent((u) -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "email " + u.getEmail() + " já é cadastrado"
                    );
                });
    }

    private UserDto userAlreadyExists(Long id) {
        return this.userRepository.findByIdParserDto(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "usuario não encontrado"
                ));
    }


}
