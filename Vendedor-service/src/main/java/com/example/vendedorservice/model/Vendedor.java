package com.example.vendedorservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendedor")
public class Vendedor {

    @Id
    private Integer vendedorId;
    private String vendedorName;
    private String vendedorPhone;
/*
    @ElementCollection
    List<String> ventasDescripcion;

    @ElementCollection
    List<String> clientesDescripcion;
    */

}
