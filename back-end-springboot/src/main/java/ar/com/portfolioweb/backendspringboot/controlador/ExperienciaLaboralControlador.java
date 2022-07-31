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

import ar.com.portfolioweb.backendspringboot.dto.ExperienciaLaboralDto;
import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.modelo.ExperienciaLaboral;
import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.modelo.TipoEmpleo;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UsuarioServicio;
import ar.com.portfolioweb.backendspringboot.servicio.ExperienciaLaboralServicio;
import ar.com.portfolioweb.backendspringboot.servicio.PersonaServicio;
import ar.com.portfolioweb.backendspringboot.servicio.TipoEmpleoServicio;

@Controller
@RequestMapping("/api/usuarios/exp_laborales")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
public class ExperienciaLaboralControlador {

    @Autowired
    private ExperienciaLaboralServicio experienciaLaboralServicio;
    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private TipoEmpleoServicio tipoEmpleoServicio;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        if (!experienciaLaboralServicio.existeExperienciaLaboralPorId(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        experienciaLaboralServicio.eliminarExperienciaLaboralPorId(id);
        return new ResponseEntity<>(new Mensaje("Datos eliminados correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    @PostMapping(value = "/exp_lab/crear/{nombreUsuario}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createExpLab(
            @RequestParam(name = "file") MultipartFile file,
            @RequestPart(name = "info") String info,
            @PathVariable("nombreUsuario") String nombreUsuario) throws JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExperienciaLaboralDto experienciaLabotalDto = mapper.readValue(info, ExperienciaLaboralDto.class);

        if (StringUtils.isBlank(experienciaLabotalDto.getNombreEmpresa()))
            return new ResponseEntity<>(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        if (experienciaLabotalDto.getFechaIngreso() == null)
            return new ResponseEntity<>(new Mensaje("Debe tener una fecha de ingreso."), HttpStatus.BAD_REQUEST);

        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona persona = personaServicio.obtenerPorId(usuario.getId()).get();
        TipoEmpleo tipoEmpleoPorId = tipoEmpleoServicio.obtenerTipoEmpleoPorId(experienciaLabotalDto.getTipoEmpleo())
                .get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ExperienciaLaboral nuevaExpLab = new ExperienciaLaboral();
        
        nuevaExpLab.setTipoEmpleo(tipoEmpleoPorId);
        nuevaExpLab.setNombreEmpresa(experienciaLabotalDto.getNombreEmpresa());
        nuevaExpLab.setEsTrabajoActual(experienciaLabotalDto.getEsTrabajoActual());
        nuevaExpLab.setFechaIngreso(LocalDate.parse(experienciaLabotalDto.getFechaIngreso(), formatter));
        if(experienciaLabotalDto.getFechaEgreso() == null) {
            nuevaExpLab.setFechaEgreso(null);
        }else {
            nuevaExpLab.setFechaEgreso(LocalDate.parse(experienciaLabotalDto.getFechaEgreso(), formatter));
        }
        nuevaExpLab.setPuesto(experienciaLabotalDto.getPuesto());
        nuevaExpLab.setDescripcion(experienciaLabotalDto.getDescripcion());
        nuevaExpLab.setIsologoEmpresa(file.getBytes());
        



        nuevaExpLab.setPersona(persona);
        experienciaLaboralServicio.guardarExperienciaLaboral(nuevaExpLab);
        return new ResponseEntity<>(new Mensaje("Experiencia laboral creada"), HttpStatus.OK);
    }

    @GetMapping(path = { "/obtener/{nombreUsuario}" })
    public ResponseEntity<?> obtenerExpLabPorPersona(@PathVariable("nombreUsuario") String nombreUsuario) {

        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        List<ExperienciaLaboral> setExpLab = experienciaLaboralServicio.obtenerPorPersonaId(usuarioId);
        return new ResponseEntity<List<ExperienciaLaboral>>(setExpLab, HttpStatus.OK);
    }


    @GetMapping(path = "/exp_lab/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        if (!experienciaLaboralServicio.existeExperienciaLaboralPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa experiencia laboral"), HttpStatus.NOT_FOUND);
        ExperienciaLaboral experienciaLaboral = experienciaLaboralServicio.obtenerExperienciaLaboralPorId(id).get();

        return new ResponseEntity<ExperienciaLaboral>(experienciaLaboral, HttpStatus.OK);
    }

    @DeleteMapping(path = "/exp_lab/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mensaje> borrarExpLab(@PathVariable Integer id) {
        if (!experienciaLaboralServicio.existeExperienciaLaboralPorId(id))
            return new ResponseEntity<>(new Mensaje("No existe esa experiencia laboral"), HttpStatus.NOT_FOUND);
       experienciaLaboralServicio.eliminarExperienciaLaboralPorId(id);
       return new ResponseEntity<>(new Mensaje("Experiencia laboral borrada correctamente"), HttpStatus.OK);
    }

    @PostMapping(path = "/exp_lab/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateExpLab(
        @Nullable @RequestParam(name = "file", required = false) MultipartFile file,
        @RequestPart(name = "info") String info,
        @PathVariable("id") Integer id) throws JsonProcessingException, IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExperienciaLaboralDto experienciaLabotalDto = mapper.readValue(info, ExperienciaLaboralDto.class);
        
        ExperienciaLaboral expLabActualizar = experienciaLaboralServicio.obtenerExperienciaLaboralPorId(id).get();

       
            
        TipoEmpleo tipoEmpleoPorId = tipoEmpleoServicio.obtenerTipoEmpleoPorId(experienciaLabotalDto.getTipoEmpleo()).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        expLabActualizar.setTipoEmpleo(tipoEmpleoPorId);
        expLabActualizar.setNombreEmpresa(experienciaLabotalDto.getNombreEmpresa());
        expLabActualizar.setEsTrabajoActual(experienciaLabotalDto.getEsTrabajoActual());
        expLabActualizar.setFechaIngreso(LocalDate.parse(experienciaLabotalDto.getFechaIngreso(), formatter));

        if(experienciaLabotalDto.getFechaEgreso() == null) {
            expLabActualizar.setFechaEgreso(null);
        }else {
            expLabActualizar.setFechaEgreso(LocalDate.parse(experienciaLabotalDto.getFechaEgreso(), formatter));
        }
        expLabActualizar.setPuesto(experienciaLabotalDto.getPuesto());
        expLabActualizar.setDescripcion(experienciaLabotalDto.getDescripcion());
        if (file != null && !file.isEmpty()) {
         expLabActualizar.setIsologoEmpresa(file.getBytes());
        }
    
        experienciaLaboralServicio.guardarExperienciaLaboral(expLabActualizar);

        return new ResponseEntity<>(new Mensaje("Experiencia laboral actualizada correctamente"), HttpStatus.OK);
    }

}