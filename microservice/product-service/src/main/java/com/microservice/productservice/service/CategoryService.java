package com.microservice.productservice.service;

import com.microservice.productservice.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    void create(CategoryDto categoryDto);
    void update(Long id, CategoryDto categoryDto);
    void delete(Long id);
}
