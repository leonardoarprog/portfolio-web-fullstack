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

import ar.com.portfolioweb.backendspringboot.modelo.Localidad;
import ar.com.portfolioweb.backendspringboot.modelo.Pais;
import ar.com.portfolioweb.backendspringboot.modelo.Provincia;
import ar.com.portfolioweb.backendspringboot.modelo.Ubicacion;
import ar.com.portfolioweb.backendspringboot.servicio.LocalidadServicio;

@Controller
@RequestMapping(path = "/api/usuarios")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class LocalidadControlador {

    @Autowired
    private LocalidadServicio localidadServicio;

    @GetMapping(path = "localidades/buscar/provincia_id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Localidad>> listarLocalidadesPorProvinciaId(@PathVariable Integer id) {
        List<Localidad> lista = localidadServicio.obtenerPorProvinciaId(id);
        return new ResponseEntity<List<Localidad>>(lista, HttpStatus.OK);
    }

    @GetMapping(path = "localidades/buscar/default")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ubicacion> obtenerUbicacionDefault() {

        Localidad localidadDefault = localidadServicio.obtenerPrimera();
        Provincia provinciaDefault = localidadDefault.getProvincia();
        Pais paisDefault = provinciaDefault.getPais();

        Ubicacion ubicacionDefault = new Ubicacion(localidadDefault, provinciaDefault, paisDefault);
        return new ResponseEntity<Ubicacion>(ubicacionDefault, HttpStatus.OK);
    } 

}
