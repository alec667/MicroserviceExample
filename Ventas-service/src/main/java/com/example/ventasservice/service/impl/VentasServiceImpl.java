package com.example.ventasservice.service.impl;

import com.example.ventasservice.dao.VentasDAO;
import com.example.ventasservice.exception.VentaNotFoundException;
import com.example.ventasservice.model.Ventas;
import com.example.ventasservice.service.VentasService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentasServiceImpl implements VentasService {

    final
    VentasDAO dao;

    public VentasServiceImpl(VentasDAO dao) {
        this.dao = dao;
    }

    @Override
    public Ventas getVenta(Integer ventaId) {
        Ventas venta = new Ventas();
        Optional<Ventas> optional = dao.findById(ventaId);
        if (optional.isPresent()) {
            venta = optional.get();
            return venta;
        } else {
            throw new VentaNotFoundException("Venta no encontrada");
        }
    }

    @Override
    public List<Ventas> getAllVentas() {
        List<Ventas> ventasList = new ArrayList<>();
        ventasList = dao.findAll();
        return ventasList;
    }

    @Override
    public String createVenta(Ventas ventas) {
        try {
            dao.save(ventas);
            return "Venta creada";
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    @Override
    public Ventas updateVenta(Ventas ventas) {
        Ventas updatedVenta = new Ventas();
        Optional<Ventas> optional = dao.findById(ventas.getVentaId());
        if (optional.isPresent()) {
            updatedVenta = optional.get();
            updatedVenta.setProducto(ventas.getProducto());
            dao.save(updatedVenta);
            return updatedVenta;
        } else {
            createVenta(ventas);
            return ventas;
        }

    }

    @Override
    public String deleteVenta(Integer ventaId) {
        Optional<Ventas> optional = dao.findById(ventaId);
        if (optional.isPresent()) {
            dao.deleteById(ventaId);
            return "Venta Id: " + ventaId + " BORRADA.";
        } else {
            return "Venta Id: " + ventaId + " No Existe.";
        }
    }

    @Override
    public List<String> getAllByVendedor(Integer vendedorId) {
        List<String> productos = new ArrayList<>();
        //TODO this
        List<Ventas> ventasByVendedor = dao.findByVendedorId(vendedorId);
        for (Ventas v: ventasByVendedor) {
            productos.add(v.getProducto());
        }
        return productos;
    }
}
