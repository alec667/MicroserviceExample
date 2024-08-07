package com.example.clientesservice.controller;

import com.example.clientesservice.model.Cliente;
import com.example.clientesservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping(path = "{clienteId}", produces = "application/json")
    public ResponseEntity<Cliente> getCliente(@PathVariable("clienteId") Integer clienteId) {
        return new ResponseEntity<>(service.getCliente(clienteId), HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(service.getAllCliente(), HttpStatus.OK);
    }

    @PostMapping(path = "/create",consumes = "application/json")
    public ResponseEntity<String> createCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(service.createCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(service.updateCliente(cliente), HttpStatus.OK);
    }

    @DeleteMapping(path = "{clienteId}")
    public ResponseEntity<String> deleteCliente(@PathVariable("clienteId") Integer clienteId) {
        return new ResponseEntity<>(service.deleteCliente(clienteId), HttpStatus.OK);
    }

    @GetMapping(path = "vendedor/{vName}")
    public ResponseEntity<List<Cliente>> getByVendedorName(@PathVariable("vName") String name){
        return new ResponseEntity<>(service.getByVendedorName(name), HttpStatus.OK);
    }

}
