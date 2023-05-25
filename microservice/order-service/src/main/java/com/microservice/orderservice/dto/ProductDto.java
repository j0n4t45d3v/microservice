package com.microservice.orderservice.dto;

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
