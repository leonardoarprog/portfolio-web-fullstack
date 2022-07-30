package ar.com.portfolioweb.backendspringboot.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.portfolioweb.backendspringboot.modelo.Habilidad;
import ar.com.portfolioweb.backendspringboot.servicio.HabilidadServicio;

@Controller
@RequestMapping(path = "/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class HabilidadControlador {

    @Autowired
    private HabilidadServicio habilidadServicio;

    @GetMapping(path = "/habilidades/buscar/categoria_habilidad_id/{id}" )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Habilidad>> listarHabilidadesPorCategoriaId(@PathVariable Integer id) {
        List<Habilidad> lista = habilidadServicio.obtenerPorCategoriaHabilidadId(id);
        return new ResponseEntity<List<Habilidad>>(lista, HttpStatus.OK);
    }

}