package com.example.users.controller;

import com.example.users.record.UserRequest;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            var users = this.userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody UserRequest userRequest) {
        try {
            this.userService.createUser(userRequest);
            return ResponseEntity.ok("Usuario criado com sucesso!");
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

}
