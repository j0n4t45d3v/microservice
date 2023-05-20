package com.microservice.productservice.service.implementations;

import com.microservice.productservice.dto.CategoryDto;
import com.microservice.productservice.model.Category;
import com.microservice.productservice.repository.CategoryRepository;
import com.microservice.productservice.service.CategoryService;
import io.javatab.microservices.util.MapperCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        return this.categoryRepository.findAll()
                .stream().map(this::transformCategoryToCategoryDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return this.categoryRepository.findById(id)
                .map(this::transformCategoryToCategoryDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "produto não encontrado"
                ));
    }

    @Override
    public void create(CategoryDto categoryDto) {
        this.categoryRepository.findByName(categoryDto.name())
                .ifPresent(c -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "categoria " + c.getName().toUpperCase() + " já ta cadastrada"
                    );
                });

        Category category = Category.builder()
                .name(categoryDto.name())
                .build();

        this.categoryRepository.save(category);
    }

    @Override
    public void update(Long id, CategoryDto categoryDto) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "categoria não encontrada"
                ));

        this.copyNotNullProperties(categoryDto, category);
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "categoria não encontrada"
                ));

        this.categoryRepository.deleteById(id);
    }

    private CategoryDto transformCategoryToCategoryDto(Category category) {
        return new CategoryDto(category.getName());
    }

    private void copyNotNullProperties(CategoryDto dto, Category category) {
        MapperCustom.updatePropererIfNotNull(category::setName, dto.name());
    }
}
