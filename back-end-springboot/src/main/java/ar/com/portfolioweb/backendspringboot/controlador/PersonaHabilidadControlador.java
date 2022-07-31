package ar.com.portfolioweb.backendspringboot.controlador;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.dto.PersonaHabilidadDto;
import ar.com.portfolioweb.backendspringboot.modelo.Habilidad;
import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.modelo.PersonaHabilidad;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UsuarioServicio;
import ar.com.portfolioweb.backendspringboot.servicio.HabilidadServicio;
import ar.com.portfolioweb.backendspringboot.servicio.PersonaHabilidadServicio;
import ar.com.portfolioweb.backendspringboot.servicio.PersonaServicio;

@Controller
@RequestMapping("/api/usuarios/habilidades")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
public class PersonaHabilidadControlador {

    @Autowired
    private PersonaHabilidadServicio personaHabilidadServicio;
    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private HabilidadServicio habilidadServicio;

    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    @PostMapping(path = "/habilidad/crear/{nombreUsuario}")

    public ResponseEntity<?> createPersonaHabilidad(@RequestBody PersonaHabilidadDto personaHabilidadDto,
            @PathVariable("nombreUsuario") String nombreUsuario) {
             Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona persona = personaServicio.obtenerPorId(usuario.getId()).get();
        Habilidad habilidad = habilidadServicio.obtenerHabilidadPorId(personaHabilidadDto.getHabilidad()).get();
        Integer score = personaHabilidadDto.getScore();
        PersonaHabilidad nuevaPersHab = new PersonaHabilidad(persona, habilidad, score);
        personaHabilidadServicio.guardarPersonaHabilidad(nuevaPersHab);

        return new ResponseEntity<>(new Mensaje("Habilidad creada"), HttpStatus.OK);
    }

    @GetMapping(path = { "/obtener/{nombreUsuario}" })
    public ResponseEntity<?> obtenerPersHabPersona(@PathVariable("nombreUsuario") String nombreUsuario) {

        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona persona = personaServicio.obtenerPorId(usuario.getId()).get();
        Integer personaId = persona.getId();
        List<PersonaHabilidad> listExpLab = personaHabilidadServicio.obtenerPorPersonaId(personaId);
        return new ResponseEntity<List<PersonaHabilidad>>(listExpLab, HttpStatus.OK);
    }

    @DeleteMapping(path = "/habilidad/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mensaje> borrarPersHab(@PathVariable Integer id) {
        if (!personaHabilidadServicio.existePersonaHabilidadPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa habilidad"), HttpStatus.NOT_FOUND);
        personaHabilidadServicio.eliminarPersonaHabilidadPorId(id);
        return new ResponseEntity<>(new Mensaje("Habilidad borrada correctamente"), HttpStatus.OK);
    }

    @GetMapping(path = "/habilidad/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerPersonaHbiliadPorId(@PathVariable Integer id) {
        if (!personaHabilidadServicio.existePersonaHabilidadPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa Habilidad"),
                    HttpStatus.NOT_FOUND);
        PersonaHabilidad personaHabilidad = personaHabilidadServicio.obtenerPersonaHabilidadPorId(id).get();
        return new ResponseEntity<PersonaHabilidad>(personaHabilidad, HttpStatus.OK);
    }

    @PostMapping(path = "/habilidad/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mensaje> updatePersonaHabilidadScore(@RequestBody Integer score,
            @PathVariable("id") Integer id)
            throws IOException {
        if (!personaHabilidadServicio.existePersonaHabilidadPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa Habilidad"), HttpStatus.NOT_FOUND);
        personaHabilidadServicio.actualizarHabilidadScore(id, score);
        return new ResponseEntity<>(new Mensaje("Datos guardados satisfactoriamente"), HttpStatus.CREATED);
    }

    @GetMapping(path = { "/habilidad/score/obtener/{id}" })
    public ResponseEntity<Object> getScore(@PathVariable("id") Integer id) throws IOException {
       
        Optional<Optional<Integer>> checkNull = Optional.ofNullable(personaHabilidadServicio.obtenerHabilidadScore(id));

        if (checkNull.get().isEmpty()) {
            String JsonStringDbSobreMi = "{\"valor\":\"" + "\"}";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(JsonStringDbSobreMi);
            return new ResponseEntity<Object>(jsonNode, HttpStatus.OK);
        }

        else {
            final Integer dbScore = personaHabilidadServicio.obtenerHabilidadScore(id).get();
            String JsonStringDbSobreMi = "{\"valor\":\"" + dbScore + "\"}";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(JsonStringDbSobreMi);
            return new ResponseEntity<Object>(jsonNode, HttpStatus.OK);
        }

    }

}
