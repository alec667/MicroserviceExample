package com.example.ventasservice.controller;

import com.example.ventasservice.model.Ventas;
import com.example.ventasservice.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ventas")
public class VentasController {

    @Autowired
    VentasService ventasService;

    @GetMapping(path = "{ventaId}", produces = "application/json")
    public ResponseEntity<Ventas> getVenta(@PathVariable("ventaId") Integer ventaId) {
        return new ResponseEntity<>(ventasService.getVenta(ventaId), HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Ventas>> getAllVentas() {
        return new ResponseEntity<>(ventasService.getAllVentas(), HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<String> createVenta(@RequestBody Ventas venta) {
        return new ResponseEntity<>(ventasService.createVenta(venta), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Ventas> updateVenta(@RequestBody Ventas venta) {
        return new ResponseEntity<>(ventasService.updateVenta(venta), HttpStatus.OK);
    }

    @DeleteMapping(path = "{ventaId}")
    public ResponseEntity<String> deleteVenta(@PathVariable("ventaId") Integer ventaId) {
        return new ResponseEntity<>(ventasService.deleteVenta(ventaId), HttpStatus.OK);
    }

    //feign
    @GetMapping(path = "/vendedor/{vendedorId}")
    public ResponseEntity<List<Ventas>> getAllByVendedor(@PathVariable("vendedorId") Integer vendedorId){
        return new ResponseEntity<>(ventasService.getAllByVendedor(vendedorId), HttpStatus.OK);
    }

}
