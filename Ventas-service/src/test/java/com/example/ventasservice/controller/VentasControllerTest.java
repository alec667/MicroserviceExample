package com.example.ventasservice.controller;

import com.example.ventasservice.model.Ventas;
import com.example.ventasservice.service.VentasService;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VentasController.class)
class VentasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VentasService ventasService;

    @InjectMocks
    VentasController ventasController;

    private AutoCloseable autoCloseable;
    Ventas testVenta1, testVenta2, testVenta3, testVenta4;
    List<Ventas> testList;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ventasController).build();

        testVenta1 = new Ventas(1,1, "product 1");
        testVenta2 = new Ventas(2, 1,"product 2");
        testVenta3 = new Ventas(3,1, "product 1");
        testVenta4 = new Ventas(4,2, "product 2");
        testList = Arrays.asList(testVenta1, testVenta2);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getVenta() throws Exception {
        when(ventasService.getVenta(1)).thenReturn(testVenta1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/ventas/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.producto", Matchers.is("product 1")));
    }

    @Test
    void getAllVentas() throws Exception {
        when(ventasService.getAllVentas()).thenReturn(testList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/ventas/all")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].producto", is("product 1")))
                .andExpect(jsonPath("$[1].producto", is("product 2")));
    }

    @Test
    void createVenta() throws Exception {
        Ventas nuevaVenta = new Ventas(5,2, "product 3");
        String content = objectWriter.writeValueAsString(nuevaVenta);

        when(ventasService.createVenta(nuevaVenta)).thenReturn("Venta creada");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/ventas/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("Venta creada"));

    }

    @Test
    void updateVenta() throws Exception {
        Ventas updatedVenta = new Ventas(1,1, "product 2");
        String content = objectMapper.writeValueAsString(updatedVenta);

        when(ventasService.updateVenta(updatedVenta)).thenReturn(updatedVenta);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.producto", is("product 2")));
    }

    @Test
    void deleteVenta() throws Exception {
        when(ventasService.deleteVenta(1)).thenReturn("Venta Id: 1 BORRADA.");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/ventas/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo("Venta Id: 1 BORRADA."));
    }

    @Test
    void getAllByVendedor() throws Exception {
        /*List<Ventas> vendedores = Arrays.asList(testVenta1, testVenta2, testVenta3);
        when(ventasService.getAllByVendedor(1)).thenReturn(vendedores);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/ventas/vendedor/1").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));*/
    }
}