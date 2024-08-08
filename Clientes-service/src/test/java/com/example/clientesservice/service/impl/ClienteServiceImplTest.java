package com.example.clientesservice.service.impl;

import com.example.clientesservice.dao.ClienteDAO;
import com.example.clientesservice.model.Cliente;
import com.example.clientesservice.service.ClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteDAO dao;

    private ClienteService clienteService;
    AutoCloseable autoCloseable;
    Cliente testCliente1, testCliente2, testCliente3, testCliente4;
    List<Cliente> testList;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.clienteService = new ClienteServiceImpl(dao);

        testCliente1 = new Cliente(1, "cliente name 1", "vendor 1");
        testCliente2 = new Cliente(2, "cliente name 2", "vendor 2");
        testCliente3 = new Cliente(3, "cliente name 3", "vendor 1");
        testCliente4 = new Cliente(4, "cliente name 4", "vendor 2");
        testList = Arrays.asList(testCliente1, testCliente2, testCliente3, testCliente4);
        dao.save(testCliente1);
        dao.save(testCliente2);
        dao.save(testCliente3);
        dao.save(testCliente4);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        dao.deleteAll();
    }

    @Test
    void getCliente() {
        mock(Cliente.class);
        mock(ClienteDAO.class);

        when(dao.findById(1)).thenReturn(Optional.ofNullable(testCliente1));
        assertThat(clienteService.getCliente(1)).isEqualTo(testCliente1);
    }

    @Test
    void getAllCliente() {
        mock(Cliente.class);
        mock(ClienteDAO.class);

        when(dao.findAll()).thenReturn(testList);
        assertThat(clienteService.getAllCliente()).isEqualTo(testList);
    }

    @Test
    void createCliente() {
        mock(Cliente.class);
        mock(ClienteDAO.class);

        Cliente nuevoCliente = new Cliente(3, "cliente name 3", "vendor 3");

        when(dao.save(nuevoCliente)).thenReturn(nuevoCliente);
        assertThat(clienteService.createCliente(nuevoCliente)).isEqualTo("Cliente creado");

    }

    @Test
    void updateCliente() {
        mock(Cliente.class);
        mock(ClienteDAO.class);

        Cliente updatedCliente = new Cliente(1, "cliente name 1", "description 1 (updated)");

        when(dao.save(updatedCliente)).thenReturn(updatedCliente);
        assertThat(clienteService.updateCliente(updatedCliente)).isEqualTo(updatedCliente);
    }

    @Test
    void deleteCliente() {
        mock(Cliente.class);
        mock(ClienteDAO.class, CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(dao).deleteById(1);
        assertThat(clienteService.deleteCliente(1)).isEqualTo("Cliente Id: 1 No Existe");
    }

    @Test
    void getByClienteName(){
        mock(Cliente.class);
        mock(ClienteDAO.class);

        List<Cliente> clientesV1= Arrays.asList(testCliente1, testCliente3);
        List<String> vendedorNames = Arrays.asList(testCliente1.getVendedorAsociadoName(), testCliente3.getVendedorAsociadoName());

        when(dao.findByVendedorAsociadoName("vendor 1")).thenReturn(clientesV1);
        assertThat(clienteService.getByVendedorName("vendor 1")).isEqualTo(vendedorNames);
    }
}