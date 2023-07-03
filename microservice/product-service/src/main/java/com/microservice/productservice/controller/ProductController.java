package com.microservice.productservice.controller;

import com.microservice.productservice.dto.Message;
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
    public ResponseEntity<Message> create(@RequestBody ProductDto productDto) {

        this.productService.create(productDto);
        var response = new Message("produto criado");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {

        this.productService.update(id, productDto);
        var response = new Message("produto atualizado");
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id") Long id) {

        this.productService.delete(id);
        var response = new Message("produto deletado");
        return ResponseEntity.ok(response);

    }
}
