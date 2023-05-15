package com.example.users.testservice;

import com.example.users.model.User;
import com.example.users.record.UserRequest;
import com.example.users.repository.UserRepository;
import com.example.users.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testUpdateUser() {
//        User user = User.builder()
//                .id(UUID.randomUUID())
//                .name("Teste")
//                .email("test@email.com")
//                .password("123456")
//                .birthDate(null)
//                .build();
//
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        UserRequest userUpdate = UserRequest.builder()
//                .name("Update nome")
//                .password("1230236")
//                .build();
//
//        userService.updateByEmail(user.getEmail(), userUpdate);
//
//        verify(userRepository, times(1)).findByEmail(user.getEmail());
//        verify(userRepository, times(1)).save(user);
//
//        assertEquals(userUpdate.name(), user.getName());
//        assertEquals(userUpdate.email(), user.getEmail());
    }


}
