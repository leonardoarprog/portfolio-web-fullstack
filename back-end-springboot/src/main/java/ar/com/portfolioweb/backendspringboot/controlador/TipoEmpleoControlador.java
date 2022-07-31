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

import ar.com.portfolioweb.backendspringboot.modelo.TipoEmpleo;
import ar.com.portfolioweb.backendspringboot.servicio.TipoEmpleoServicio;

@Controller
@RequestMapping("/api/usuarios")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")

public class TipoEmpleoControlador {

    @Autowired
    private TipoEmpleoServicio tipoEmpleoServicio;

    
    
    @GetMapping(path = "/tipos_de_empleos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<TipoEmpleo>> listarTiposdeEmpleo() {
        Set<TipoEmpleo> lista = tipoEmpleoServicio.obtenerTodos();
        return new ResponseEntity<Set<TipoEmpleo>>(lista, HttpStatus.OK);
    }
    
}
