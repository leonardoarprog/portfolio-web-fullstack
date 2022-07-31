package ar.com.portfolioweb.backendspringboot.controlador;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.portfolioweb.backendspringboot.modelo.CategoriaHabilidad;
import ar.com.portfolioweb.backendspringboot.servicio.CategoriaHabilidadServicio;

@Controller
@RequestMapping("/api/usuarios")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")

public class CategoriaHabilidadControlador {

    @Autowired
    private CategoriaHabilidadServicio categoriaHabilidadServicio;

    @GetMapping(path = "/categorias_habilidad")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<CategoriaHabilidad>> listarTiposdeEmpleo() {
        Set<CategoriaHabilidad> lista = categoriaHabilidadServicio.obtenerTodos();
        return new ResponseEntity<Set<CategoriaHabilidad>>(lista, HttpStatus.OK);
    }

}