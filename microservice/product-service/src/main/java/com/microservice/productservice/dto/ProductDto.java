package com.microservice.productservice.dto;

import com.microservice.productservice.model.Brand;
import com.microservice.productservice.model.Category;
import com.microservice.productservice.model.Product;

import java.util.List;

public record ProductDto(
        String name,
        String description,
        Double price,
        Brand brand,
        Long stock,
        List<Category> categories
) {

    public static ProductDto transformProductToProductDto(Product product) {
        return new ProductDto(product.getName(), product.getDescription(), product.getPrice(), product.getBrand(), product.getStock(), product.getCategories());
    }
}
