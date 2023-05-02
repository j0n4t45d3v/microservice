package com.example.users.service;


import com.example.users.model.User;
import com.example.users.record.UserRequest;
import com.example.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public void createUser(UserRequest userRequest) {
        userRepository.findByEmail(userRequest.email())
                .ifPresent((u) -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
                });

        User user = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .birthDate(userRequest.birthDate())
                .build();

        userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
