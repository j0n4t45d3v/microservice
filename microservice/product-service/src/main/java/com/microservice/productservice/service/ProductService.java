package com.microservice.productservice.service;

import com.microservice.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    void create(ProductDto productDto);
    void update(Long id, ProductDto productDto);
    void delete(Long id);
}
