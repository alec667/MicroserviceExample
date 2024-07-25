package com.example.vendedorservice.dao;

import com.example.vendedorservice.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorDAO extends JpaRepository<Vendedor, Integer> {
}
