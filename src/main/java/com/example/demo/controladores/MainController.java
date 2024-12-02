package com.example.demo.controladores;

import com.example.demo.persistencia.modelos.Proveedor;
import com.example.demo.servicios.LoginServicio;
import com.example.demo.servicios.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private LoginServicio loginServicio;

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/")
    public String login(@RequestParam String nombre, @RequestParam String password, Model model) {
        String mensaje = loginServicio.login(nombre, password);
       if(mensaje.equals("Confirmado")) {
            return "redirect:/proveedores";
        }
        model.addAttribute("mensaje", mensaje);
        return "login";
    }

    @GetMapping("/proveedores")
    public String home(Model model) {
        model.addAttribute("proveedores", proveedorServicio.getAllProductos());
        return "proveedores";
    }

    @GetMapping("/proveedores/nuevo")
    public String nuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "formulario";
    }

    @PostMapping("/proveedores/crear")
    public String guardarProveedor(
            @RequestParam String nombre,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String email,
            @RequestParam String sitioWeb,
            @RequestParam Integer estado) {

        // Crear el objeto Producto con los valores recibidos
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        proveedor.setEmail(email);
        proveedor.setSitioWeb(sitioWeb);
        proveedor.setEstado(estado);

        // Guardar el producto
        proveedorServicio.guardarProveedor(proveedor);

        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/editar/{id}")
    public String editarProveedor(@PathVariable Long id, Model model) {
        Proveedor proveedor = proveedorServicio.getProveedorById(id);
        if (proveedor != null) {
            model.addAttribute("proveedor", proveedor);
        } else {
            model.addAttribute("proveedor", new Proveedor());
        }
        return "formulario";
    }

    @PostMapping("/proveedores/actualizar")
    public String actualizarProveedor(
            @RequestParam Long id,
            @RequestParam String nombre,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String email,
            @RequestParam String sitioWeb,
            @RequestParam Integer estado) {

        // Obtener el proveedor existente
        Proveedor proveedor = proveedorServicio.getProveedorById(id);

        if (proveedor != null) {
            // Actualizar sus campos
            proveedor.setNombre(nombre);
            proveedor.setTelefono(telefono);
            proveedor.setDireccion(direccion);
            proveedor.setEmail(email);
            proveedor.setSitioWeb(sitioWeb);
            proveedor.setEstado(estado);

            // Guardar los cambios
            proveedorServicio.guardarProveedor(proveedor);
        }

        return "redirect:/proveedores";
    }


    @GetMapping("/proveedores/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id) {
        proveedorServicio.borrarProveedor(id);
        return "redirect:/proveedores";
    }
}
