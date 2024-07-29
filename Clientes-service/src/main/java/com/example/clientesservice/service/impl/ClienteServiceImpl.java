package com.example.clientesservice.service.impl;

import com.example.clientesservice.dao.ClienteDAO;
import com.example.clientesservice.exception.ClienteNotFoundException;
import com.example.clientesservice.model.Cliente;
import com.example.clientesservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    final
    ClienteDAO dao;

    public ClienteServiceImpl(ClienteDAO dao) {
        this.dao = dao;
    }

    @Override
    public Cliente getCliente(Integer clienteId) {
        Cliente client = new Cliente();
        Optional<Cliente> optional = dao.findById(clienteId);
        if (optional.isPresent()) {
            client = optional.get();
            return client;
        } else {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }
    }

    @Override
    public List<Cliente> getAllCliente() {
        List<Cliente> clienteList = new ArrayList<>();
        clienteList = dao.findAll();
        return clienteList;
    }

    @Override
    public String createCliente(Cliente cliente) {
        try {
            dao.save(cliente);
            return "Cliente creado";
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        Cliente updatedClient = new Cliente();
        Optional<Cliente> optional = dao.findById(cliente.getClienteId());
        if (optional.isPresent()) {
            updatedClient = optional.get();
            updatedClient.setClienteName(cliente.getClienteName());
            updatedClient.setDescripcion(cliente.getDescripcion());

            dao.save(updatedClient);
            return updatedClient;
        } else {
            createCliente(cliente);
            return cliente;
        }

    }

    @Override
    public String deleteCliente(Integer clienteId) {
        Optional<Cliente> optional = dao.findById(clienteId);
        if (optional.isEmpty()) {
            return "Cliente Id: " + clienteId + " No Existe";
        } else {
            dao.deleteById(clienteId);
            return "Cliente Id: " + clienteId + " BORRADO.";
        }
    }
}
