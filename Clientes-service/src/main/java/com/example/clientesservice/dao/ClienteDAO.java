package com.example.clientesservice.dao;

import com.example.clientesservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {
   List<Cliente> findByVendedorAsociadoName(String name);
}
