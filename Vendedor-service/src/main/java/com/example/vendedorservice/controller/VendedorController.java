package com.example.vendedorservice.controller;


import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vendedor")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;

    @PostMapping(path = "create", consumes = "application/json")
    public ResponseEntity<String> createVendedor(@RequestBody Vendedor vendedor) {
        return new ResponseEntity<>(vendedorService.createVendedor(vendedor), HttpStatus.CREATED);
    }

    @GetMapping(path = "{vendedorId}", produces = "application/json")
    public ResponseEntity<Vendedor> getVendedor(@PathVariable("vendedorId") Integer vendedorId) {
        return new ResponseEntity<>(vendedorService.getVendedor(vendedorId), HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Vendedor>> getAllVendedor() {
        return new ResponseEntity<>(vendedorService.getAllVendedor(), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Vendedor> updateVendedor(@RequestBody Vendedor vendedor) {
        return new ResponseEntity<>(vendedorService.updateVendedor(vendedor), HttpStatus.OK);
    }

    @DeleteMapping(path = "{vendedorId}")
    public ResponseEntity<String> deleteVendedor(@PathVariable("vendedorId") Integer vendedorId) {
        return new ResponseEntity<>(vendedorService.deleteVendedor(vendedorId), HttpStatus.OK);
    }

}
