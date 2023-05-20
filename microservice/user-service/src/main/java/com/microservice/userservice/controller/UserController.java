package com.microservice.userservice.controller;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            var users = this.userService.getAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            var user = this.userService.getById(id);
            return ResponseEntity.ok(user);
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto body) {
        try {
            this.userService.create(body);
            return ResponseEntity.ok("usuario criado");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDto body) {
        try {
            this.userService.update(id, body);
            return ResponseEntity.ok("usuario atualizado");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            this.userService.delete(id);
            return ResponseEntity.ok("usuario deletado");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
