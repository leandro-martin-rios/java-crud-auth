package com.example.demo.persistencia.repositorios;

import com.example.demo.persistencia.modelos.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {
}
