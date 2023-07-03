package com.microservice.productservice.controller;

import com.microservice.productservice.dto.ProductDto;
import com.microservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        var products = this.productService.getAll();
        return ResponseEntity.ok(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        var product = this.productService.getById(id);
        return ResponseEntity.ok(product);

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) {

        this.productService.create(productDto);
        return ResponseEntity.ok("produto criado");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {

        this.productService.update(id, productDto);
        return ResponseEntity.ok("produto atualizado");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        this.productService.delete(id);
        return ResponseEntity.ok("produto deletado");

    }
}
