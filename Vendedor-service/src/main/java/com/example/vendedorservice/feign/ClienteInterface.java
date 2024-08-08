package com.example.vendedorservice.feign;

import com.example.vendedorservice.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("clientes-service")
public interface ClienteInterface {

    @GetMapping(path = "/clientes/{clienteId}")
    ResponseEntity<Cliente> getCliente(@PathVariable("clienteId") Integer clienteId);

    @GetMapping(path = "/clientes/vendedor/{vName}")
    ResponseEntity<List<String>> getByVendedorName(@PathVariable("vName") String name);
}
