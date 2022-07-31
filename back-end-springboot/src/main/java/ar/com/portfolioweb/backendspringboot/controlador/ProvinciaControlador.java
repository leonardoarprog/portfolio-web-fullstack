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

import ar.com.portfolioweb.backendspringboot.modelo.Provincia;
import ar.com.portfolioweb.backendspringboot.servicio.ProvinciaServicio;

@Controller
@RequestMapping(path = "/api/usuarios")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
public class ProvinciaControlador {

    @Autowired
    private ProvinciaServicio provinciaServicio;

    @GetMapping(path = "/provincias/buscar/pais_id/{id}" )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Provincia>> listarProvinciasPorPaisId(@PathVariable Integer id) {
        List<Provincia> lista = provinciaServicio.obtenerPorPaisId(id);
        return new ResponseEntity<List<Provincia>>(lista, HttpStatus.OK);
    }

}