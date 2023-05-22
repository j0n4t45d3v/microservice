package com.microservice.productservice.controller;

import com.microservice.productservice.dto.BrandDto;
import com.microservice.productservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            var brands = this.brandService.getAll();
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            var brand = this.brandService.getById(id);
            return ResponseEntity.ok(brand);
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandDto brandDto) {
        try {
            this.brandService.create(brandDto);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BrandDto brandDto) {
        try {
            this.brandService.update(id, brandDto);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            this.brandService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException error) {
            return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
