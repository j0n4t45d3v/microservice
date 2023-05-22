package com.microservice.productservice.dto;

import com.microservice.productservice.model.Brand;

public record BrandDto(
        String name
) {

    public static BrandDto BrandFromBrandDto(Brand brand) {
        return new BrandDto(brand.getName());
    }
}
