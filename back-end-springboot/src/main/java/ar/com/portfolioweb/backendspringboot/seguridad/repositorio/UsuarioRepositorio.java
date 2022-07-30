package ar.com.portfolioweb.backendspringboot.seguridad.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findById(Integer id);

    List<Usuario> findAll();

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsById(Integer id);

}
