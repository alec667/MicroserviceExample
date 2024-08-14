package com.example.vendedorservice.controller;

import com.example.vendedorservice.model.Vendedor;
import com.example.vendedorservice.service.VendedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendedorController.class)
class VendedorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    VendedorService vendedorService;

    @InjectMocks
    VendedorController vendedorController;

    AutoCloseable autoCloseable;
    Vendedor testVendedor1, testVendedor2;
    List<Vendedor> testList;
    List<String> ventasDesc1, clientes1, ventasDesc2, clientes2;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(vendedorController).build();

        ventasDesc1 = Arrays.asList("product 1", "product 2", "product 3");
        clientes1 = Arrays.asList("client 1", "client 2");
        ventasDesc2 = Arrays.asList("product 4", "product 5", "product 6");
        clientes2 = Arrays.asList("client 3", "client 4");
        testVendedor1 = new Vendedor(1, "name1", "phone 1", ventasDesc1, clientes1);
        testVendedor2 = new Vendedor(2, "name2", "phone 2", ventasDesc2, clientes2);
        testList = Arrays.asList(testVendedor1, testVendedor2);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createVendedor() throws Exception {
        Vendedor nuevoVendedor = new Vendedor(3, "name3", "phone 3", ventasDesc2, clientes2);
        String content = objectWriter.writeValueAsString(nuevoVendedor);
        when(vendedorService.createVendedor(nuevoVendedor)).thenReturn("Vendedor creado");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/vendedor/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("Vendedor creado"));
    }

    @Test
    void getVendedor() throws Exception {
        when(vendedorService.getVendedor(1)).thenReturn(testVendedor1);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/vendedor/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.vendedorName", Matchers.is("name1")));
    }

    @Test
    void getAllVendedor() throws Exception {
        when(vendedorService.getAllVendedor()).thenReturn(testList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/vendedor/all")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].vendedorName", is("name1")));
    }

    @Test
    void updateVendedor() throws Exception {
        Vendedor updatedVendedor = new Vendedor(2, "name2", "phone 2 (updated)", ventasDesc2, clientes2);
        String content = objectWriter.writeValueAsString(updatedVendedor);
        when(vendedorService.updateVendedor(updatedVendedor)).thenReturn(updatedVendedor);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/vendedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.vendedorPhone", is("phone 2 (updated)")));
    }

    @Test
    void deleteVendedor() throws Exception {
        when(vendedorService.deleteVendedor(2)).thenReturn("Vendedor Id: 2 BORRADO.");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/vendedor/2");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("Vendedor Id: 2 BORRADO."));
    }
}