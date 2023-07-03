package com.microservice.userservice.controller;

import com.microservice.userservice.dto.Message;
import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {

        var users = this.userService.getAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {

        var user = this.userService.getById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<Message> create(@RequestBody UserDto body) {

        this.userService.create(body);
        var response = new Message("usuario criado");
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") Long id, @RequestBody UserDto body) {

        this.userService.update(id, body);
        var response = new Message("usuario atualizado");
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id") Long id) {

        this.userService.delete(id);
        var response = new Message("usuario deletado");
        return ResponseEntity.ok(response);

    }

}
