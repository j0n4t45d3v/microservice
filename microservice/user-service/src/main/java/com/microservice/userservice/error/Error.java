package com.microservice.userservice.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({"error", "status"})
public record Error(
        String error,
        Integer status,
        LocalDateTime timestamp
) {
}
