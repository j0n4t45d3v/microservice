package com.microservice.productservice.service;

import com.microservice.productservice.dto.BrandDto;

import java.util.List;

public interface BrandService {

    List<BrandDto> getAll();
    BrandDto getById(Long id);
    void create(BrandDto brandDto);
    void update(Long id, BrandDto brandDto);
    void delete(Long id);

}
