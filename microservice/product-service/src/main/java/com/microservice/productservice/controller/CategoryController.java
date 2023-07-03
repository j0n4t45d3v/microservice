package com.microservice.productservice.controller;

import com.microservice.productservice.dto.CategoryDto;
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
    public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {

            this.categoryService.create(categoryDto);
            return ResponseEntity.ok("categoria criada");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {

            this.categoryService.update(id, categoryDto);
            return ResponseEntity.ok("categoria atualizado");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

            this.categoryService.delete(id);
            return ResponseEntity.ok("categoria deletado");

    }

}
