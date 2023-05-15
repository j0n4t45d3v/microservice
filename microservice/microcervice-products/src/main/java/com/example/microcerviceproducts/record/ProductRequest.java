package com.example.microcerviceproducts.record;

public record ProductRequest(
        String name,
        String description,
        double price
) {
}
