package com.example.ventasservice.dao;

import com.example.ventasservice.model.Ventas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VentasDAOTest {

    @Autowired
    private VentasDAO dao;

    Ventas testVenta1, testVenta2, testVenta3, testVenta4;
    List<Ventas> testList;

    @BeforeEach
    void setUp() {
        testVenta1 = new Ventas(1, 1,"product 1");
        testVenta2 = new Ventas(2,1, "product 2");
        testVenta3 = new Ventas(3, 1,"product 1");
        testVenta4 = new Ventas(4,2, "product 2");
        dao.save(testVenta1);
        dao.save(testVenta2);
        dao.save(testVenta3);
        dao.save(testVenta4);
    }

    @AfterEach
    void tearDown() {
        testVenta1 = null;
        testVenta2 = null;
        testVenta3 = null;
        testVenta4 = null;
        dao.deleteAll();
    }

    @Test
    void findAllByVendedorId() {
        testList = dao.findAllByVendedorId(1);

        assertThat(testList.get(0).getVentaId()).isEqualTo(1);
        assertThat(testList.get(1).getVentaId()).isEqualTo(2);
    }
}