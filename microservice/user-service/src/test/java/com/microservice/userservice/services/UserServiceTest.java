package com.microservice.userservice.services;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.model.User;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private List<UserDto> user;

    @BeforeEach
    void setup() {
        User user1 = User.builder()
                .email("john_doe@gmail.com")
                .password("123456")
                .username("john_doe")
                .build();

        User user2 = User.builder()
                .email("john_doe@gmail.com")
                .password("123456")
                .username("john_doe")
                .build();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        this.user = users.stream()
                .map(UserDto::new)
                .toList();
    }

    @Test
    void getAll() {

        var mockResponse = this.user;

        when(this.userRepository.findAllParserDto()).thenReturn(this.user);

        var response = this.userService.getAll();

        assertEquals(mockResponse, response);
        assertEquals(mockResponse.size(), response.size());
        assertTrue(response.size() > 0);
        assertNotNull(response);

    }

}
