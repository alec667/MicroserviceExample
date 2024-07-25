package com.example.vendedorservice.controller;


import com.example.vendedorservice.model.Vendedor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "vendedor")
public class VendedorController {

    @PostMapping(path = "create", consumes = "application/json")
    public ResponseEntity<String> createVendedor(@RequestBody Vendedor vendedor){
        return null;
    }

}
