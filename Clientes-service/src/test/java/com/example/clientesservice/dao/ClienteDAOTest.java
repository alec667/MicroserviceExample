package com.example.clientesservice.dao;

import com.example.clientesservice.model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteDAOTest {

    @Autowired
    ClienteDAO clienteDAO;

    Cliente testCliente1, testCliente2, testCliente3, testCliente4;
    List<Cliente> testList1, testList2;

    @BeforeEach
    void setUp() {
        testCliente1 = new Cliente(1, "test name 1", "vendor 1");
        testCliente2 = new Cliente(2, "test name 2", "vendor 2");
        testCliente3 = new Cliente(3, "test name 3", "vendor 1");
        testCliente4 = new Cliente(4, "test name 4", "vendor 2");

        clienteDAO.save(testCliente1);
        clienteDAO.save(testCliente2);
        clienteDAO.save(testCliente3);
        clienteDAO.save(testCliente4);
    }

    @AfterEach
    void tearDown() {
        testCliente1 = null;
        testCliente2 = null;
        testCliente3 = null;
        testCliente4 = null;
        clienteDAO.deleteAll();
    }

    @Test
    void findByClienteName() {
        testList1 = Arrays.asList(testCliente1,  testCliente3);
        testList2 = Arrays.asList(testCliente2,  testCliente4);

        assertThat(clienteDAO.findByVendedorAsociadoName("vendor 1")).isEqualTo(testList1);
        assertThat(clienteDAO.findByVendedorAsociadoName("vendor 2")).isEqualTo(testList2);

    }
}