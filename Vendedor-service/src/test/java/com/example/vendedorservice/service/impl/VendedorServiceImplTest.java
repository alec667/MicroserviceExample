package com.example.vendedorservice.service.impl;

import com.example.vendedorservice.dao.VendedorDAO;
import com.example.vendedorservice.feign.ClienteInterface;
import com.example.vendedorservice.feign.VentasInterface;
import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VendedorServiceImplTest {

    @Mock
    private VendedorDAO dao;
    @Mock
    private VentasInterface ventasInterface;
    @Mock
    private ClienteInterface clienteInterface;

    private VendedorService vendedorService;
    AutoCloseable autoCloseable;
    Vendedor testVendedor1, testVendedor2;
    List<Vendedor> testList;
    List<String> ventasDesc1, clientes1, ventasDesc2, clientes2;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.vendedorService = new VendedorServiceImpl(dao, ventasInterface, clienteInterface);

        ventasDesc1 = Arrays.asList("product 1", "product 2", "product 3");
        clientes1 = Arrays.asList("client 1", "client 2");
        testVendedor1 = new Vendedor(1, "name1", "phone 1", ventasDesc1, clientes1);
        testVendedor2 = new Vendedor(2, "name2", "phone 2", ventasDesc2, clientes2);
        testList = Arrays.asList(testVendedor1, testVendedor2);
        dao.save(testVendedor1);
        dao.save(testVendedor2);

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
        mock(ClienteInterface.class);

        ResponseEntity<List<String>> responseVen = new ResponseEntity<>(ventasDesc1, HttpStatus.OK);
        when(ventasInterface.getAllByVendedor(1)).thenReturn(responseVen);
        ResponseEntity<List<String>> responseCli = new ResponseEntity<>(clientes1, HttpStatus.OK);
        when(clienteInterface.getByVendedorName("name1")).thenReturn(responseCli);

        when(dao.findById(1)).thenReturn(Optional.ofNullable(testVendedor1));
        assertThat(vendedorService.getVendedor(1)).isEqualTo(testVendedor1);
    }

    @Test
    void getAllVendedor() {
        mock(Vendedor.class);
        mock(VendedorDAO.class);
        mock(VentasInterface.class);
        mock(ClienteInterface.class);

        ResponseEntity<List<String>> responseVen = new ResponseEntity<>(ventasDesc1, HttpStatus.OK);
        when(ventasInterface.getAllByVendedor(1)).thenReturn(responseVen);
        ResponseEntity<List<String>> responseCli = new ResponseEntity<>(clientes1, HttpStatus.OK);
        when(clienteInterface.getByVendedorName("name1")).thenReturn(responseCli);
        ResponseEntity<List<String>> responseVen2 = new ResponseEntity<>(ventasDesc1, HttpStatus.OK);
        when(ventasInterface.getAllByVendedor(2)).thenReturn(responseVen2);
        ResponseEntity<List<String>> responseCli2 = new ResponseEntity<>(clientes1, HttpStatus.OK);
        when(clienteInterface.getByVendedorName("name2")).thenReturn(responseCli2);

        when(dao.findAll()).thenReturn(testList);
        assertThat(vendedorService.getAllVendedor()).isEqualTo(testList);

    }

    @Test
    void createVendedor() {
        mock(Vendedor.class);
        mock(VendedorDAO.class);

        List<String> ventasDesc3 = Arrays.asList("product 1", "product 2", "product 3");
        List<String> clientes3 = Arrays.asList("client 1", "client 2");
        Vendedor nuevoVendedor = new Vendedor(3, "name3", "phone 3", ventasDesc3, clientes3);

        when(dao.save(nuevoVendedor)).thenReturn(nuevoVendedor);
        assertThat(vendedorService.createVendedor(nuevoVendedor)).isEqualTo("Vendedor creado");
    }

    @Test
    void updateVendedor() {
        mock(Vendedor.class);
        mock(VendedorDAO.class);

        Vendedor updatedVendedor = new Vendedor(2, "name2(updated)", "phone 2", ventasDesc2, clientes2);

        when(dao.findById(2)).thenReturn(Optional.of(updatedVendedor));
        assertThat(vendedorService.updateVendedor(updatedVendedor)).isEqualTo(updatedVendedor);
    }

    @Test
    void deleteVendedor() {
        mock(Vendedor.class);
        mock(VendedorDAO.class, CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(dao).deleteById(1);
        assertThat(vendedorService.deleteVendedor(1)).isEqualTo("Vendedor Id: 1 No Existe.");

    }
}