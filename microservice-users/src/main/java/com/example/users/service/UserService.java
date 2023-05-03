package com.example.users.service;


import com.example.users.model.User;
import com.example.users.record.UserRequest;
import com.example.users.repository.UserRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public void deleteByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        userOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        userOptional.ifPresent((user) -> this.userRepository.delete(user));
    }

    public void updateByEmail(String email, UserRequest userUpdate) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        userOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(UserRequest.class, User.class)
                .addMappings(m -> m.map(UserRequest::name ,User::setName))
                .addMappings(m -> m.map(UserRequest::email ,User::setEmail))
                .addMappings(m -> m.map(UserRequest::password ,User::setPassword))
                .addMappings(m -> m.map(UserRequest::birthDate ,User::setBirthDate));

        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        User user = userOptional.get();
        mapper.map(userUpdate, user);

        this.userRepository.save(user);
    }

}
