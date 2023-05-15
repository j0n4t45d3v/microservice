package com.example.microcerviceproducts.controller;

import com.example.microcerviceproducts.record.ProductRequest;
import com.example.microcerviceproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            var products = this.productService.getAll();
            return ResponseEntity.ok(products);
        } catch (ResponseStatusException error) {
            return  ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch(Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ProductRequest productRequest){
        try {
           this.productService.createProduct(productRequest);
            return ResponseEntity.ok("Produto adicionado com sucesso");
        } catch (ResponseStatusException error) {
            return  ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch(Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable("name") String name){
        try {
           this.productService.deleteProduct(name);
            return ResponseEntity.ok("Produto removido com sucesso");
        } catch (ResponseStatusException error) {
            return  ResponseEntity.status(error.getStatusCode()).body(error.getReason());
        } catch(Exception error) {
            return ResponseEntity.internalServerError().body(error.getMessage());
        }
    }
}
