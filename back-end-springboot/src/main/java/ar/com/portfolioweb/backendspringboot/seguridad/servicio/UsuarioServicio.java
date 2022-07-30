package ar.com.portfolioweb.backendspringboot.seguridad.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.seguridad.repositorio.UsuarioRepositorio;

@Service
@Transactional
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> obtenerTodoslosUsuarios() {
        List<Usuario> lista = usuarioRepositorio.findAll();
        return lista;
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    public Optional<Usuario> obtenerUsuarioPorNombre(String nombreUsuario) {
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }

    public void guardarUsuario(Usuario usuario) {

        Persona persona = new Persona(usuario, null, null, null, null, null, null, null, null, null,
                null, null, null, null);
        usuario.setPersona(persona);
        usuarioRepositorio.save(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {

        usuarioRepositorio.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public boolean existePorNombreUsuario(String nombreUsuario) {
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existeUsuarioPorId(Integer id) {
        return usuarioRepositorio.existsById(id);
    }

}