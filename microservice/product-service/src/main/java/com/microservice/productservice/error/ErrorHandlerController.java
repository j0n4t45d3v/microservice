package com.microservice.productservice.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Error> errorHandler(ResponseStatusException error) {
        String msg = error.getReason();
        int code = error.getStatusCode().value();
        Error errorResponse = new Error(msg, code);
        return ResponseEntity.status(code).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Error errorHandler(Exception error) {
        return new Error(error.getMessage(), 500);
    }
}
