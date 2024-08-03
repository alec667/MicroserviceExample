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
    Cliente testCliente1, testCliente2;
    List<Cliente> testList;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.clienteService = new ClienteServiceImpl(dao);

        testCliente1 = new Cliente(1, "test name 1", "test description 1");
        testCliente2 = new Cliente(2, "test name 2", "test description 2");
        testList = Arrays.asList(testCliente1, testCliente2);
        dao.save(testCliente1);
        dao.save(testCliente2);
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

        Cliente nuevoCliente = new Cliente(3, "cliente name 3", "description 3");

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
}