package com.example.ventasservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VentaNotFoundExceptionHandler {

    @ExceptionHandler(VentaNotFoundException.class)
    public ResponseEntity<Object> ventaNotFound(VentaNotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
