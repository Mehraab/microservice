package com.example.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> globalException(OrderNotFoundException ex) {
        return new ResponseEntity<>("Handling globally "+ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> globalException(ItemNotFoundException ex) {
        return new ResponseEntity<>("Handling globally "+ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
