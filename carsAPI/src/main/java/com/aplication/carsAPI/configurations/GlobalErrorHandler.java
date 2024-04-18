package com.aplication.carsAPI.configurations;


import com.aplication.carsAPI.errors.ApiErrors;
import com.aplication.carsAPI.errors.ValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrors> status(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(new ApiErrors(ex.getReason(), ex.getStatusCode()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> validation(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiErrors(BAD_REQUEST, ValidationError.of(ex)));
    }
}
