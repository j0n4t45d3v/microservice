package com.example.users.record;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserRequest(
        String name,
        String email,
        String password,
        LocalDate birthDate
) {
}
