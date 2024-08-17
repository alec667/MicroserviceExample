package com.example.vendedorservice.service.impl;

import com.example.vendedorservice.dao.VendedorDAO;
import com.example.vendedorservice.exception.VendedorNotFoundException;
import com.example.vendedorservice.feign.ClienteInterface;
import com.example.vendedorservice.feign.VentasInterface;
import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements VendedorService {
    final
    ClienteInterface clienteInterface;
    final
    VentasInterface ventasInterface;
    final
    VendedorDAO dao;

    public VendedorServiceImpl(VendedorDAO dao, VentasInterface ventasInterface, ClienteInterface clienteInterface) {
        this.dao = dao;
        this.ventasInterface = ventasInterface;
        this.clienteInterface = clienteInterface;
    }

    @Override
    public Vendedor getVendedor(Integer vendedorId) {
        Vendedor vendedor;
        Optional<Vendedor> optional = dao.findById(vendedorId);
        if (optional.isPresent()) {
            vendedor = optional.get();
            List<String> ventasDesc = ventasInterface.getAllByVendedor(vendedorId).getBody();
            List<String> clientesNames = clienteInterface.getByVendedorName(vendedor.getVendedorName()).getBody();
            vendedor.setVentasDescripcion(ventasDesc);
            vendedor.setClientes(clientesNames);
            return vendedor;
        } else {
            throw new VendedorNotFoundException("Vendedor no encontrado");
        }

    }

    @Override
    public List<Vendedor> getAllVendedor() {
        List<Vendedor> vendedorList = new ArrayList<>();
        vendedorList = dao.findAll();
        for (Vendedor v : vendedorList) {
            v.setVentasDescripcion(ventasInterface.getAllByVendedor(v.getVendedorId()).getBody());
            v.setClientes(clienteInterface.getByVendedorName(v.getVendedorName()).getBody());
        }
        return vendedorList;
    }

    @Override
    public String createVendedor(Vendedor vendedor) {
        try {
            Vendedor nuevoVendedor = new Vendedor();
            nuevoVendedor.setVendedorId(vendedor.getVendedorId());
            nuevoVendedor.setVendedorName(vendedor.getVendedorName());
            nuevoVendedor.setVendedorPhone(vendedor.getVendedorPhone());
            dao.save(vendedor);
            return "Vendedor creado";
        } catch (NullPointerException e) {
            return "Vendedor no puede ser null";
        }
    }

    @Override
    public Vendedor updateVendedor(Vendedor vendedor) {
        Vendedor updatedVendedor = new Vendedor();
        Optional<Vendedor> optional = dao.findById(vendedor.getVendedorId());
        if (optional.isPresent()) {
            updatedVendedor = optional.get();
            updatedVendedor.setVendedorName(vendedor.getVendedorName());
            updatedVendedor.setVendedorPhone(vendedor.getVendedorPhone());
            dao.save(updatedVendedor);
            return updatedVendedor;
        } else {
            createVendedor(vendedor);
            return vendedor;
        }
    }

    @Override
    public String deleteVendedor(Integer vendedorId) {
        Optional<Vendedor> optional = dao.findById(vendedorId);
        if (optional.isPresent()) {
            dao.deleteById(vendedorId);
            return "Vendedor Id: " + vendedorId + " BORRADO.";
        } else {
            return "Vendedor Id: " + vendedorId + " No Existe.";
        }
    }
}
