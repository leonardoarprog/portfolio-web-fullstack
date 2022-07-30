package ar.com.portfolioweb.backendspringboot.seguridad.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.portfolioweb.backendspringboot.dto.Mensaje;
import ar.com.portfolioweb.backendspringboot.seguridad.dto.JwtDto;
import ar.com.portfolioweb.backendspringboot.seguridad.dto.LoginUsuario;
import ar.com.portfolioweb.backendspringboot.seguridad.dto.NuevoUsuario;
import ar.com.portfolioweb.backendspringboot.seguridad.enums.RolNombre;
import ar.com.portfolioweb.backendspringboot.seguridad.jwt.JwtProvider;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Rol;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.RolServicio;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UsuarioServicio;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthControlador {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioServicio usuarioService;

    @Autowired
    RolServicio rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo_usuario")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos vacíos"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existePorNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario(nuevoUsuario.getNombreUsuario(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<String> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
        for (String rol : rolesStr) {
            switch (rol) {
                case "admin":
                    Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
                    roles.add(rolAdmin);
                    break;
                default:
                    Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
                    roles.add(rolUser);
            }
        }
        usuario.setRoles(roles);
        usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

   /* @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos vacíos"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }*/



    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
       
        if (bindingResult.hasErrors())
        return new ResponseEntity<>(new Mensaje("Campos vacíos"), HttpStatus.BAD_REQUEST);

        try {

            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);

            
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new Mensaje("Contraseña o usuario incorrecto"), HttpStatus.BAD_REQUEST);
        }


    }



}
