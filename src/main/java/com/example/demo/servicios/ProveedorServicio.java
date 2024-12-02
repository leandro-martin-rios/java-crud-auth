package com.example.demo.servicios;


import com.example.demo.persistencia.modelos.Proveedor;
import com.example.demo.persistencia.repositorios.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public List<Proveedor> getAllProductos() {
        return proveedorRepositorio.findAll();
    }

    public void guardarProveedor(Proveedor proveedor) {
        proveedorRepositorio.save(proveedor);
    }

    public Proveedor getProveedorById(Long idProveedor) {
        return proveedorRepositorio.findById(idProveedor).orElse(null);
    }


    public void borrarProveedor(Long idProveedor) {
        proveedorRepositorio.deleteById(idProveedor);
    }


}
