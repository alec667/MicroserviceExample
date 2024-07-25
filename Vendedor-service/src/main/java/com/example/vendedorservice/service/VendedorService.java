package com.example.vendedorservice.service;

import com.example.vendedorservice.model.Vendedor;

import java.util.List;

public interface VendedorService {

    Vendedor getVendedor(Integer vendedorId);
    List<Vendedor> getAllVendedor();
    String createVendedor(Vendedor vendedor);
    Vendedor updateVendedor(Vendedor vendedor);
    String deleteVendedor(Integer vendedorId);


}
