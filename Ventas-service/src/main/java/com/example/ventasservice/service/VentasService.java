package com.example.ventasservice.service;

import com.example.ventasservice.model.Ventas;

import java.util.List;

import org.springframework.lang.NonNull;

public interface VentasService {

    Ventas getVenta(@NonNull Integer ventaId);

    List<Ventas> getAllVentas();

    String createVenta(@NonNull Ventas ventas);

    Ventas updateVenta(@NonNull Ventas ventas);

    String deleteVenta(@NonNull Integer ventaId);

    List<String> getAllByVendedor(@NonNull Integer vendedorId);
}
