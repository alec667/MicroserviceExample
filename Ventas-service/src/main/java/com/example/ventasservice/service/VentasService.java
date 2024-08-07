package com.example.ventasservice.service;

import com.example.ventasservice.model.Ventas;

import java.util.List;

public interface VentasService {

    Ventas getVenta(Integer ventaId);
    List<Ventas> getAllVentas();
    String createVenta(Ventas ventas);
    Ventas updateVenta(Ventas ventas);
    String deleteVenta(Integer ventaId);
    List<Ventas> getAllByVendedor(Integer vendedorId);
}
