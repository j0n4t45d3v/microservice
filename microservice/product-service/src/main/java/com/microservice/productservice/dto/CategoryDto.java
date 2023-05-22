package com.microservice.productservice.dto;

import com.microservice.productservice.model.Category;

public record CategoryDto(
        String name
) {
    public static CategoryDto transformCategoryToCategoryDto(Category category) {
        return new CategoryDto(category.getName());
    }
}
