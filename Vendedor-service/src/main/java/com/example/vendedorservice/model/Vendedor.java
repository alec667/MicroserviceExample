package com.example.vendedorservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vendedor {

    @Id
    private Integer vendedorId;
    private String VendedorName;
    private String VendedorPhone;
}
