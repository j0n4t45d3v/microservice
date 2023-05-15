package com.example.users.controller;

import com.example.users.record.UserRequest;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody UserRequest userRequest) {
        try {
            this.userService.createUser(userRequest);
            return ResponseEntity.ok("Usuario criado com sucesso!");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(@PathVariable("email") String email) {
        try {
            this.userService.deleteByEmail(email);
            return ResponseEntity.ok("Usuario deletado com sucesso!");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

//    não ta pegando o update
    @PatchMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable("email") String email, @RequestBody UserRequest userRequest){
        try {
            this.userService.updateByEmail(email, userRequest);
            return ResponseEntity.ok("Usuario atualizado com sucesso!");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }
}
