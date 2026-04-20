package com.example.vendedorservice.service;

import com.example.vendedorservice.model.Vendedor;

import java.util.List;

import org.springframework.lang.NonNull;

public interface VendedorService {

    Vendedor getVendedor(@NonNull Integer vendedorId);

    List<Vendedor> getAllVendedor();

    String createVendedor(@NonNull Vendedor vendedor);

    Vendedor updateVendedor(@NonNull Vendedor vendedor);

    String deleteVendedor(@NonNull Integer vendedorId);

}
