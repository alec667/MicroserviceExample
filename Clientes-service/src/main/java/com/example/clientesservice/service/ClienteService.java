package com.example.clientesservice.service;

import com.example.clientesservice.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente getCliente(Integer clienteId);
    List<Cliente> getAllCliente();
    String createCliente(Cliente cliente);
    Cliente updateCliente(Cliente cliente);
    String deleteCliente(Integer clienteId);
}
