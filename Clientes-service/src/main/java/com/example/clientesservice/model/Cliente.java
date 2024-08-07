package com.example.clientesservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    private Integer clienteId;
    private String clienteName;
    private String vendedorAsociadoName;
}
