package com.example.vendedorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VendedorNotFoundExceptionHandler {

    @ExceptionHandler(VendedorNotFoundException.class)
    public ResponseEntity<Object> vendNotFound(VendedorNotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
