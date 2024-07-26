package com.example.vendedorservice.service.impl;

import com.example.vendedorservice.dao.VendedorDAO;
import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements VendedorService {

    final
    VendedorDAO dao;

    public VendedorServiceImpl(VendedorDAO dao) {
        this.dao = dao;
    }

    @Override
    public Vendedor getVendedor(Integer vendedorId) {
        Vendedor vendedor;
        Optional<Vendedor> optional = dao.findById(vendedorId);
        if (optional.isPresent()){
            vendedor = optional.get();
            return vendedor;
        }else {
            return null;
        }

    }

    @Override
    public List<Vendedor> getAllVendedor() {
        return null;
    }

    @Override
    public String createVendedor(Vendedor vendedor) {
        dao.save(vendedor);
        return "Vendedor creado";
    }

    @Override
    public Vendedor updateVendedor(Vendedor vendedor) {
        return null;
    }

    @Override
    public String deleteVendedor(Integer vendedorId) {
        return null;
    }
}
