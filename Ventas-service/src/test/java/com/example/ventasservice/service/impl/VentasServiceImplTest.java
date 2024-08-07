package com.example.ventasservice.service.impl;

import com.example.ventasservice.dao.VentasDAO;
import com.example.ventasservice.model.Ventas;
import com.example.ventasservice.service.VentasService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class VentasServiceImplTest {

    @Mock
    private VentasDAO ventasDAO;

    private VentasService ventasService;
    AutoCloseable autoCloseable;
    Ventas testVenta1, testVenta2, testVenta3, testVenta4;
    List<Ventas> testList;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.ventasService = new VentasServiceImpl(ventasDAO);
        testVenta1 = new Ventas(1,1 , "product 1");
        testVenta2 = new Ventas(2,1, "product 2");
        testVenta3 = new Ventas(3, 1,"product 1");
        testVenta4 = new Ventas(4, 2,"product 2");
        ventasDAO.save(testVenta1);
        ventasDAO.save(testVenta2);
        ventasDAO.save(testVenta3);
        ventasDAO.save(testVenta4);
        testList = Arrays.asList(testVenta1, testVenta2, testVenta3, testVenta4);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        ventasDAO.deleteAll();
    }

    @Test
    void getVenta() {
        mock(Ventas.class);
        mock(VentasDAO.class);

        when(ventasDAO.findById(1)).thenReturn(Optional.of(testVenta1));
        assertThat(ventasService.getVenta(1)).isEqualTo(testVenta1);
    }

    @Test
    void getAllVentas() {
        mock(Ventas.class);
        mock(VentasDAO.class);

        when(ventasDAO.findAll()).thenReturn(testList);
        assertThat(ventasService.getAllVentas()).isEqualTo(testList);
    }

    @Test
    void createVenta() {
        mock(Ventas.class);
        mock(VentasDAO.class);

        Ventas nuevaVenta = new Ventas(5,2, "product 1");

        when(ventasDAO.save(nuevaVenta)).thenReturn(nuevaVenta);
        assertThat(ventasService.createVenta(nuevaVenta)).isEqualTo("Venta creada");

    }

    @Test
    void updateVenta() {
        mock(Ventas.class);
        mock(VentasDAO.class);

        Ventas updatedVenta = new Ventas(1,1, "product 2");

        when(ventasDAO.save(updatedVenta)).thenReturn(updatedVenta);
        assertThat(ventasService.updateVenta(updatedVenta)).isEqualTo(updatedVenta);

    }

    @Test
    void deleteVenta() {
        mock(Ventas.class);
        mock(VentasDAO.class);
        String response = "Venta Id: 1 No Existe.";

        doAnswer(Answers.CALLS_REAL_METHODS).when(ventasDAO).deleteById(1);
        assertThat(ventasService.deleteVenta(1)).isEqualTo(response);
    }

    @Test
    void getAllByVendedor() {
        mock(Ventas.class);
        mock(VentasDAO.class);

        List<Ventas> vendedores = Arrays.asList(testVenta1, testVenta2, testVenta3);

        when(ventasDAO.findByVendedorId(1)).thenReturn(vendedores);
        assertThat(ventasService.getAllByVendedor(1)).isEqualTo(vendedores);
    }
}