package com.microservice.productservice.dto;

import com.microservice.productservice.model.Brand;
import com.microservice.productservice.model.Category;

import java.util.List;

public record ProductDto(
        String name,
        String description,
        Double price,
        Brand brand,
        Long stock,
        List<Category> categories
) {
}
