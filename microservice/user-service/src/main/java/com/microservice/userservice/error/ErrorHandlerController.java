package com.microservice.userservice.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Error> errorHandler(ResponseStatusException error) {
        String msg = error.getReason();
        int code = error.getStatusCode().value();
        LocalDateTime timestamp = LocalDateTime.now();
        Error errorResponse = new Error(msg, code, timestamp);
        return ResponseEntity.status(code).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Error errorHandler(Exception error) {
        LocalDateTime timestamp = LocalDateTime.now();
        return new Error(error.getMessage(), 500, timestamp);
    }

}
