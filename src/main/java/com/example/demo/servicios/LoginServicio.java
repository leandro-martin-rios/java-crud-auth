package com.example.demo.servicios;

import com.example.demo.persistencia.modelos.Usuario;
import com.example.demo.persistencia.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public String login(String nombre, String password) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario != null && usuario.getPasswordUsuario().equals(password)) {
            return "Confirmado";
        } else {
            return "USUARIO INCORRECTO";
        }
    }
}