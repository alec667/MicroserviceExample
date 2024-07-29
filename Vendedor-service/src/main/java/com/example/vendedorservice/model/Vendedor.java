package com.example.vendedorservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
