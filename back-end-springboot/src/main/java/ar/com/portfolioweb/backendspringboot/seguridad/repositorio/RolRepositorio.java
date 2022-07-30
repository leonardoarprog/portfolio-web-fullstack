package ar.com.portfolioweb.backendspringboot.seguridad.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.seguridad.enums.RolNombre;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Rol;

@Repository
public interface RolRepositorio extends CrudRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
