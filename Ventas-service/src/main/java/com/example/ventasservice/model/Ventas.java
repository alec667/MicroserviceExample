package com.example.ventasservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
public class Ventas {

    @Id
    private Integer ventaId;
    private String producto;

}
