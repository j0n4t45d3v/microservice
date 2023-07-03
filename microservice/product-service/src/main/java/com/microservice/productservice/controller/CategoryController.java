package com.microservice.productservice.controller;

import com.microservice.productservice.dto.CategoryDto;
import com.microservice.productservice.dto.Message;
import com.microservice.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        var products = this.categoryService.getAll();
        return ResponseEntity.ok(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        var product = this.categoryService.getById(id);
        return ResponseEntity.ok(product);

    }

    @PostMapping
    public ResponseEntity<Message> create(@RequestBody CategoryDto categoryDto) {

        this.categoryService.create(categoryDto);
        var response = new Message("categoria criada");
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {

        this.categoryService.update(id, categoryDto);
        var response = new Message("categoria atualizado");
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id") Long id) {

        this.categoryService.delete(id);
        var response = new Message("categoria deletado");
        return ResponseEntity.ok(response);

    }

}
