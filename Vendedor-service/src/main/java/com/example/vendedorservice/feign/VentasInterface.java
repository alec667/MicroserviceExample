package com.example.vendedorservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("ventas-service")
public interface VentasInterface {

    @GetMapping(path = "/ventas/vendedor/{vendedorId}")
    ResponseEntity<List<String>> getAllByVendedor(@PathVariable("vendedorId") Integer vendedorId);
}
