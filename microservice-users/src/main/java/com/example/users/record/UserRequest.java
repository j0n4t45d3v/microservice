package com.example.users.record;

import java.time.LocalDate;

public record UserRequest(
        String name,
        String email,
        String password,
        LocalDate birthDate
) {
}
