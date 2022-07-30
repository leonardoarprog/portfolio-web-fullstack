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

import ar.com.portfolioweb.backendspringboot.modelo.TipoEducacion;
import ar.com.portfolioweb.backendspringboot.servicio.TipoEducacionServicio;

@Controller
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")

public class TipoEducacionControlador {

    @Autowired
    private TipoEducacionServicio tipoEducacionServicio;  
    
    @GetMapping(path = "/tipos_de_educacion")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<TipoEducacion>> listarTiposdeEmpleo() {
        Set<TipoEducacion> lista = tipoEducacionServicio.obtenerTodos();
        return new ResponseEntity<Set<TipoEducacion>>(lista, HttpStatus.OK);
    }
    
}
