package ar.com.portfolioweb.backendspringboot.controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.portfolioweb.backendspringboot.dto.EducacionDto;
import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.modelo.Educacion;
import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.modelo.TipoEducacion;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UsuarioServicio;
import ar.com.portfolioweb.backendspringboot.servicio.EducacionServicio;
import ar.com.portfolioweb.backendspringboot.servicio.PersonaServicio;
import ar.com.portfolioweb.backendspringboot.servicio.TipoEducacionServicio;

@Controller
@RequestMapping("/api/usuarios/educaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionControlador {

    @Autowired
    private EducacionServicio educacionServicio;
    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private TipoEducacionServicio tipoEducacionServicio;

    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    @PostMapping(value = "/educacion/crear/{nombreUsuario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEducacion(
            @RequestParam(name = "file") MultipartFile file,
            @RequestPart(name = "info") String info,
            @PathVariable("nombreUsuario") String nombreUsuario) throws JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        EducacionDto educacionDto = mapper.readValue(info, EducacionDto.class);

        if (StringUtils.isBlank(educacionDto.getNombreEstablecimiento()))
            return new ResponseEntity<>(new Mensaje("El nombre de establecimiento educativo es obligatorio"), HttpStatus.BAD_REQUEST);
        if (educacionDto.getFechaIngreso() == null)
            return new ResponseEntity<>(new Mensaje("Debe tener una fecha de ingreso."), HttpStatus.BAD_REQUEST);

        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona persona = personaServicio.obtenerPorId(usuario.getId()).get();
        TipoEducacion tipoEducacionPorId = tipoEducacionServicio.obtenerTipoEducacionPorId(educacionDto.getTipoEducacion()).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                Educacion nuevaEducacion = new Educacion();


                nuevaEducacion.setTipoEducacion(tipoEducacionPorId);
                nuevaEducacion.setNombreEstablecimiento(educacionDto.getNombreEstablecimiento());
                nuevaEducacion.setEnCursoActual(educacionDto.getEnCursoActual());
                nuevaEducacion.setFechaIngreso(LocalDate.parse(educacionDto.getFechaIngreso(), formatter));
                if(educacionDto.getFechaEgreso() == null) {
                    nuevaEducacion.setFechaEgreso(null);
                }else {
                    nuevaEducacion.setFechaEgreso(LocalDate.parse(educacionDto.getFechaEgreso(), formatter));
                }
                nuevaEducacion.setTituloObtenido(educacionDto.getTituloObtenido());
                nuevaEducacion.setDescripcion(educacionDto.getDescripcion());
                nuevaEducacion.setIsologoEstablecimiento(file.getBytes());
                nuevaEducacion.setPersona(persona);

                
        educacionServicio.guardarEducacion(nuevaEducacion);
        return new ResponseEntity<>(new Mensaje("Educación creada"), HttpStatus.OK);
    }

    @GetMapping(path = { "/obtener/{nombreUsuario}" })
    public ResponseEntity<?> obtenerEducacionesPorPersona(@PathVariable("nombreUsuario") String nombreUsuario) {

        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        List<Educacion> setEducacion = educacionServicio.obtenerPorPersonaId(usuarioId);
        return new ResponseEntity<List<Educacion>>(setEducacion, HttpStatus.OK);
    }


    @GetMapping(path = "/educacion/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerEducacionPorId(@PathVariable Integer id) {
        if (!educacionServicio.existeEducacionPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa educacion"), HttpStatus.NOT_FOUND);
        Educacion educacion = educacionServicio.obtenerEducacionPorId(id).get();

        return new ResponseEntity<Educacion>(educacion, HttpStatus.OK);
    }

    @DeleteMapping(path = "/educacion/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mensaje> borrarEducacion(@PathVariable Integer id) {
        if (!educacionServicio.existeEducacionPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa educacion"), HttpStatus.NOT_FOUND);
       educacionServicio.eliminarEducacionPorId(id);
       return new ResponseEntity<>(new Mensaje("Educacion borrada correctamente"), HttpStatus.OK);
    }

    @PostMapping(path = "/educacion/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEducacion(
        @Nullable @RequestParam(name = "file", required = false) MultipartFile file,
        @RequestPart(name = "info") String info,
        @PathVariable("id") Integer id) throws JsonProcessingException, IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        EducacionDto educacionDto = mapper.readValue(info, EducacionDto.class);
        
        Educacion educacionActualizar = educacionServicio.obtenerEducacionPorId(id).get();

       
            
        TipoEducacion tipoEducacionPorId = tipoEducacionServicio.obtenerTipoEducacionPorId(educacionDto.getTipoEducacion()).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        educacionActualizar.setTipoEducacion(tipoEducacionPorId);
        educacionActualizar.setNombreEstablecimiento(educacionDto.getNombreEstablecimiento());
        educacionActualizar.setEnCursoActual(educacionDto.getEnCursoActual());
        educacionActualizar.setFechaIngreso(LocalDate.parse(educacionDto.getFechaIngreso(), formatter));
        if(educacionDto.getFechaEgreso() == null) {
            educacionActualizar.setFechaEgreso(null);
        }else {
            educacionActualizar.setFechaEgreso(LocalDate.parse(educacionDto.getFechaEgreso(), formatter));
        }
        educacionActualizar.setTituloObtenido(educacionDto.getTituloObtenido());
        educacionActualizar.setDescripcion(educacionDto.getDescripcion());
        if (file != null && !file.isEmpty()) {
         educacionActualizar.setIsologoEstablecimiento(file.getBytes());
        }
    
        educacionServicio.guardarEducacion(educacionActualizar);

        return new ResponseEntity<>(new Mensaje("Educacion actualizada correctamente"), HttpStatus.OK);
    }

}