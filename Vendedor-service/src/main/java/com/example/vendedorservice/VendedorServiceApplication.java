package com.example.vendedorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VendedorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendedorServiceApplication.class, args);
    }

}
