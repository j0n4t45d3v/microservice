package com.example.microcerviceproducts.service;

import com.example.microcerviceproducts.model.Product;
import com.example.microcerviceproducts.record.ProductRequest;
import com.example.microcerviceproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public void createProduct(ProductRequest productRequest) {
        this.productRepository.findByName(productRequest.name())
                .ifPresent((product) ->{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já existe");
                });

        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        this.productRepository.save(product);
    }

    public void deleteProduct(String name){
        Product product = this.productRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));

        this.productRepository.deleteById(product.getId());
    }
}
