package com.microservice.productservice.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({"error", "status", "timestamp"})
public record Error(
        String error,
        Integer status,
        LocalDateTime timestamp
) {
}