package com.microservice.productservice.dto;

public record OrderDto(
        String nameProduct,
        Integer quantityRequested
) {
}
