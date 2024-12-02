package com.example.demo.persistencia.repositorios;

import com.example.demo.persistencia.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
