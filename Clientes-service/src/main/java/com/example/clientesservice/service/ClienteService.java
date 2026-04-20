package com.example.clientesservice.service;

import com.example.clientesservice.model.Cliente;

import java.util.List;

import org.springframework.lang.NonNull;

public interface ClienteService {

    Cliente getCliente(@NonNull Integer clienteId);

    List<Cliente> getAllCliente();

    String createCliente(@NonNull Cliente cliente);

    Cliente updateCliente(@NonNull Cliente cliente);

    String deleteCliente(@NonNull Integer clienteId);

    List<String> getByVendedorName(String name);
}
