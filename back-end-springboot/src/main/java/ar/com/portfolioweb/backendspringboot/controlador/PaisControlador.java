package ar.com.portfolioweb.backendspringboot.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.portfolioweb.backendspringboot.modelo.Pais;
import ar.com.portfolioweb.backendspringboot.servicio.PaisServicio;

@Controller
@RequestMapping(path = "/api/usuarios")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class PaisControlador {

    @Autowired
    private PaisServicio paisServicio;

    @GetMapping(path = "/paises")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Pais>> listarPaises() {
        List<Pais> lista = paisServicio.obtenerTodos();
        return new ResponseEntity<List<Pais>>(lista, HttpStatus.OK);
    }

}
