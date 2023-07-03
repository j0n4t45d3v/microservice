package com.microservice.productservice.controller;

import com.microservice.productservice.dto.BrandDto;
import com.microservice.productservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        var brands = this.brandService.getAll();
        return ResponseEntity.ok(brands);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        var brand = this.brandService.getById(id);
        return ResponseEntity.ok(brand);

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandDto brandDto) {

        this.brandService.create(brandDto);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BrandDto brandDto) {

        this.brandService.update(id, brandDto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        this.brandService.delete(id);
        return ResponseEntity.ok().build();

    }
}
