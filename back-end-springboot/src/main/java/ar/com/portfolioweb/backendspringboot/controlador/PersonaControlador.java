package ar.com.portfolioweb.backendspringboot.controlador;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import ar.com.portfolioweb.backendspringboot.dto.LocalidadDto;
import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.dto.PersonaDto;
import ar.com.portfolioweb.backendspringboot.modelo.Localidad;
import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UsuarioServicio;
import ar.com.portfolioweb.backendspringboot.servicio.LocalidadServicio;
import ar.com.portfolioweb.backendspringboot.servicio.PersonaServicio;


@Controller
@RequestMapping(path = "/api/usuarios")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-web-front.web.app")
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private LocalidadServicio localidadServicio;

    @PostMapping(path = "/datos_personales/actualizar/{nombreUsuario}")
    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    public ResponseEntity<?> updatePersona(@RequestBody PersonaDto personaDto,
            @PathVariable("nombreUsuario") String nombreUsuario) {
        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Usuario currentUser = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona personaActualizar = personaServicio.obtenerPorId(currentUser.getId()).get();
        LocalidadDto localidadDto = personaDto.getUbicacion().getLocalidad();
        Localidad localidadActualizar = localidadServicio.obtenerPorId(localidadDto.getId()).get();

        personaActualizar.setNombre(personaDto.getNombre());
        personaActualizar.setApellido(personaDto.getApellido());
        personaActualizar.setFechaNacimiento(personaDto.getFechaNacimiento());
        personaActualizar.setEmail(personaDto.getEmail());
        personaActualizar.setDireccion(personaDto.getDireccion());
        personaActualizar.setLocalidad(localidadActualizar);
        personaActualizar.setPosicionLaboral(personaDto.getPosicionLaboral());
        personaActualizar.setSobreMi(personaDto.getSobreMi());
        personaActualizar.setFotoPerfil(personaDto.getFotoPerfil());
        personaActualizar.setImgBg(personaDto.getImgBg());
        personaActualizar.setUrlRepositorio(personaDto.getUrlRepositorio());
        personaActualizar.setUrlFacebook(personaDto.getUrlFacebook());
        personaActualizar.setUrlTwitter(personaDto.getUrlTwitter());

        personaServicio.guardarPersona(personaActualizar);
        return new ResponseEntity<>(new Mensaje("Datos personales actualizados"), HttpStatus.CREATED);
    }

    @PostMapping(path = "/datos_personales/foto_perfil/actualizar/{nombreUsuario}")
    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    public ResponseEntity<Mensaje> uploadImagenPerfil(@RequestParam("imagen") MultipartFile file,
            @PathVariable("nombreUsuario") String nombreUsuario)
            throws IOException {
        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        personaServicio.actualizarFotoPerfil(usuarioId, file.getBytes());
        return new ResponseEntity<>(new Mensaje("Foto de perfil guardada satisfactoriamente"), HttpStatus.CREATED);
    }

    @PostMapping(path = "/datos_personales/img_bg/actualizar/{nombreUsuario}")
    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    public ResponseEntity<Mensaje> uploadImagenBg(@RequestParam("imagen") MultipartFile file,
            @PathVariable("nombreUsuario") String nombreUsuario)
            throws IOException {

        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        personaServicio.actualizarImgBg(usuarioId, file.getBytes());
        return new ResponseEntity<>(new Mensaje("Imagen background guardada satisfactoriamente"), HttpStatus.CREATED);
    }

    @PostMapping(path = "/datos_personales/sobre_mi/actualizar/{nombreUsuario}")
    @PreAuthorize("#nombreUsuario == authentication.principal.username && hasRole('ADMIN')")
    public ResponseEntity<Mensaje> updateSobreMi(@RequestBody String sobreMi,
            @PathVariable("nombreUsuario") String nombreUsuario)
            throws IOException {
        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        personaServicio.actualizarsobreMi(usuarioId, sobreMi);
        return new ResponseEntity<>(new Mensaje("Datos guardados satisfactoriamente"), HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/datos_personales/sobre_mi/borrar/{nombreUsuario}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mensaje> borrarSobreMi(@PathVariable("nombreUsuario") String nombreUsuario)
            throws IOException {
        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
        return new ResponseEntity<>(new Mensaje("No existe ese persona"), HttpStatus.NOT_FOUND);
        Integer usuarioId = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get().getId();
        personaServicio.borrarSobreMi(usuarioId);
        return new ResponseEntity<>(new Mensaje("Datos borrados corectamente"), HttpStatus.OK);
    }


    @GetMapping("/username/{nombreUsuario}")
    public ResponseEntity<?> getOne(@PathVariable String nombreUsuario) {
        if (!usuarioServicio.existePorNombreUsuario(nombreUsuario))
            return new ResponseEntity<>(new Mensaje("No existe esa usuario"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        Persona persona = personaServicio.obtenerPorId(usuario.getId()).get();
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

}