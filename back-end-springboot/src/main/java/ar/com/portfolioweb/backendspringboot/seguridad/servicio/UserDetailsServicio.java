package ar.com.portfolioweb.backendspringboot.seguridad.servicio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.UsuarioPrincipal;



@Service
public class UserDetailsServicio implements UserDetailsService {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombre(nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
}