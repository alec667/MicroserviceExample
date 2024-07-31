package com.example.ventasservice.dao;


import com.example.ventasservice.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasDAO extends JpaRepository<Ventas, Integer> {
}
