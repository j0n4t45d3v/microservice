package com.microservice.productservice.controller;

import com.microservice.productservice.dto.CategoryDto;
import com.microservice.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            var products = this.categoryService.getAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            var product = this.categoryService.getById(id);
            return ResponseEntity.ok(product);
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {
        try {
            this.categoryService.create(categoryDto);
            return ResponseEntity.ok("categoria criada");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        try {
            this.categoryService.update(id, categoryDto);
            return ResponseEntity.ok("categoria atualizado");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            this.categoryService.delete(id);
            return ResponseEntity.ok("categoria deletado");
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
