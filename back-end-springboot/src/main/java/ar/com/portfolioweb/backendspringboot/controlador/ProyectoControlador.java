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

import ar.com.portfolioweb.backendspringboot.dto.ProyectoDto;
import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.modelo.Proyecto;
import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UsuarioServicio;
import ar.com.portfolioweb.backendspringboot.servicio.PersonaServicio;
import ar.com.portfolioweb.backendspringboot.servicio.ProyectoServicio;

@Controller
@RequestMapping("/api/usuarios/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoControlador {

    @Autowired
    private ProyectoServicio proyectoServicio;
    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/proyecto/crear/{nombreUsuario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createExpLab(
        @Nullable @RequestParam(name = "file1", required = false) MultipartFile file1,
        @Nullable @RequestParam(name = "file2", required = false) MultipartFile file2,
        @Nullable @RequestParam(name = "file3", required = false) MultipartFile file3,
            @RequestPart(name = "info") String info,
            @PathVariable("nombreUsuario") String nombreUsuario) throws JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ProyectoDto proyectoDto = mapper.readValue(info, ProyectoDto.class);

        if (StringUtils.isBlank(proyectoDto.getNombreProyecto()))
            return new ResponseEntity<>(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        if (proyectoDto.getFechaRealizacion() == null)
            return new ResponseEntity<>(new Mensaje("Debe tener una fecha de realizacion."), HttpStatus.BAD_REQUEST);

        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona persona = personaServicio.obtenerPorId(usuario.getId()).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
                Proyecto nuevoProyecto = new Proyecto();
                nuevoProyecto.setPersona(persona);
                nuevoProyecto.setNombreProyecto(proyectoDto.getNombreProyecto());
                nuevoProyecto.setFechaRealizacion(LocalDate.parse(proyectoDto.getFechaRealizacion(), formatter));
                nuevoProyecto.setDescripcion(proyectoDto.getDescripcion());
                nuevoProyecto.setUrlProyecto(proyectoDto.getUrlProyecto());

                if (file1 != null && !file1.isEmpty()) {
                    nuevoProyecto.setImgProyecto1(file1.getBytes());
                }
   
                if (file2 != null && !file2.isEmpty()) {
                    nuevoProyecto.setImgProyecto2(file2.getBytes());
                }
    
                if (file3 != null && !file3.isEmpty()) {
                    nuevoProyecto.setImgProyecto3(file3.getBytes());
                }

        proyectoServicio.guardarProyecto(nuevoProyecto);
        return new ResponseEntity<>(new Mensaje("Proyecto guardado"), HttpStatus.OK);
    }

    @GetMapping(path = { "/obtener/{nombreUsuario}" })
    public ResponseEntity<?> obtenerProyectosPorPersona(@PathVariable("nombreUsuario") String nombreUsuario) {

        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        List<Proyecto> setExpLab = proyectoServicio.obtenerPorPersonaId(usuarioId);
        return new ResponseEntity<List<Proyecto>>(setExpLab, HttpStatus.OK);
    }

    @GetMapping(path = "/proyecto/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerProyectoPorId(@PathVariable Integer id) {
        if (!proyectoServicio.existeProyectoPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe ese proyecto"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = proyectoServicio.obtenerProyectosPorId(id).get();
        return new ResponseEntity<Proyecto>(proyecto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/proyecto/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mensaje> borrarProyecto(@PathVariable Integer id) {
        if (!proyectoServicio.existeProyectoPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe ese proyecto"), HttpStatus.NOT_FOUND);
        proyectoServicio.eliminarProyectoPorId(id);
        return new ResponseEntity<>(new Mensaje("Proyecto borrado correctamente"), HttpStatus.OK);
    }

    @PostMapping(path = "/proyecto/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateExpLab(
            @Nullable @RequestParam(name = "file1", required = false) MultipartFile file1,
            @Nullable @RequestParam(name = "file2", required = false) MultipartFile file2,
            @Nullable @RequestParam(name = "file3", required = false) MultipartFile file3,
            @RequestPart(name = "info") String info,
            @PathVariable("id") Integer id) throws JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ProyectoDto proyectoDto = mapper.readValue(info, ProyectoDto.class);

        Proyecto proyectoActualizar = proyectoServicio.obtenerProyectosPorId(id).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        proyectoActualizar.setNombreProyecto(proyectoDto.getNombreProyecto());
        proyectoActualizar.setFechaRealizacion(LocalDate.parse(proyectoDto.getFechaRealizacion(), formatter));
        proyectoActualizar.setDescripcion(proyectoDto.getDescripcion());
        proyectoActualizar.setUrlProyecto(proyectoDto.getUrlProyecto());
        if (file1 != null && !file1.isEmpty()) {
            proyectoActualizar.setImgProyecto1(file1.getBytes());
        }
        ;
        if (file2 != null && !file2.isEmpty()) {
            proyectoActualizar.setImgProyecto2(file2.getBytes());
        }
        ;
        if (file3 != null && !file3.isEmpty()) {
            proyectoActualizar.setImgProyecto3(file3.getBytes());
        }
        ;

        proyectoServicio.guardarProyecto(proyectoActualizar);

        return new ResponseEntity<>(new Mensaje("Proyecto actualizado correctamente"), HttpStatus.OK);
    }

}