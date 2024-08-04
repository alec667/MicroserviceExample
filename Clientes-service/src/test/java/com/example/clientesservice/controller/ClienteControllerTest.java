package com.example.clientesservice.controller;

import com.example.clientesservice.model.Cliente;
import com.example.clientesservice.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    AutoCloseable autoCloseable;
    Cliente testCliente1, testCliente2;
    List<Cliente> testList;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        testCliente1 = new Cliente(1, "test name 1", "test description 1");
        testCliente2 = new Cliente(2, "test name 2", "test description 2");
        testList = Arrays.asList(testCliente1, testCliente2);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getCliente() throws Exception {
        when(clienteService.getCliente(1)).thenReturn(testCliente1);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.clienteName", is("test name 1")));
    }

    @Test
    void getAllClientes() throws Exception {
        when(clienteService.getAllCliente()).thenReturn(testList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/clientes/all")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].clienteName", is("test name 1")))
                .andExpect(jsonPath("$[1].descripcion", is("test description 2")));
    }

    @Test
    void createCliente() throws Exception {
        Cliente nuevoCliente = new Cliente(3, "cliente name 3", "description 3");
        String content = objectWriter.writeValueAsString(nuevoCliente);

        when(clienteService.createCliente(nuevoCliente)).thenReturn("Cliente creado");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/clientes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("Cliente creado"));
    }

    @Test
    void updateCliente() throws Exception {
        Cliente updatedCliente = new Cliente(1, "cliente name 1", "description 1 (updated)");
        String content = objectWriter.writeValueAsString(updatedCliente);

        when(clienteService.updateCliente(updatedCliente)).thenReturn(updatedCliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.descripcion", is("description 1 (updated)")));
    }

    @Test
    void deleteCliente() throws Exception {
        when(clienteService.deleteCliente(1)).thenReturn("Cliente Id: 1 BORRADO.");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/clientes/1");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("Cliente Id: 1 BORRADO."));
    }
}