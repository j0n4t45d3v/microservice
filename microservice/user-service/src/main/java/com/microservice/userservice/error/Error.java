package com.microservice.userservice.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"error", "status"})
public record Error(
        String error,
        Integer status
) {
}
