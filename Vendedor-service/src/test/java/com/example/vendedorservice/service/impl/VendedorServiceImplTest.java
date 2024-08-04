package com.example.vendedorservice.service.impl;

import com.example.vendedorservice.dao.VendedorDAO;
import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VendedorServiceImplTest {

    @Mock
   private VendedorDAO dao;

    private VendedorService vendedorService;
    AutoCloseable autoCloseable;
    Vendedor testVendedor1, testVendedor2;
    List<Vendedor> testList;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.vendedorService = new VendedorServiceImpl(dao);

        testVendedor1 = new Vendedor(1, "name1", "phone 1");

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