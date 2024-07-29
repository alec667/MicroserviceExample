package com.example.clientesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClienteNotFoundExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Object> clientNotFound(ClienteNotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
