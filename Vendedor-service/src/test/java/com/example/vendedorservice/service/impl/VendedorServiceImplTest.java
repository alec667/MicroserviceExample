package com.example.vendedorservice.service.impl;

import com.example.vendedorservice.dao.VendedorDAO;
import com.example.vendedorservice.feign.ClienteInterface;
import com.example.vendedorservice.feign.VentasInterface;
import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VendedorServiceImplTest {

    @Mock
    private VendedorDAO dao;
    @Mock
    private VentasInterface ventasInterface;

    @Mock
    ClienteInterface clienteInterface;

    private VendedorService vendedorService;
    AutoCloseable autoCloseable;
    Vendedor testVendedor1, testVendedor2;
    List<Vendedor> testList;
    List<String> ventasDesc, clientes;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.vendedorService = new VendedorServiceImpl(dao, ventasInterface, clienteInterface);

        ventasDesc = Arrays.asList("product 1", "product 2", "product 3");
        clientes = Arrays.asList("client 1", "client 2");
        testVendedor1 = new Vendedor(1, "name1", "phone 1", ventasDesc, clientes);
        dao.save(testVendedor1);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        dao.deleteAll();
    }

    @Test
    void getVendedor() {
        mock(Vendedor.class);
        mock(VendedorDAO.class);
        mock(VentasInterface.class);

        ResponseEntity<List<String>> response = new ResponseEntity<>(ventasDesc, HttpStatus.OK);
        when(ventasInterface.getAllByVendedor(1)).thenReturn(response);

        when(dao.findById(1)).thenReturn(Optional.ofNullable(testVendedor1));
        assertThat(vendedorService.getVendedor(1)).isEqualTo(testVendedor1);
    }

    @Test
    void getAllVendedor() {
    }

    @Test
    void createVendedor() {
    }

    @Test
    void updateVendedor() {
    }

    @Test
    void deleteVendedor() {
    }
}