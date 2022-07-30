package ar.com.portfolioweb.backendspringboot.seguridad.servicio;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.seguridad.enums.RolNombre;
import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Rol;
import ar.com.portfolioweb.backendspringboot.seguridad.repositorio.RolRepositorio;

@Service
@Transactional
public class RolServicio {

    @Autowired
    RolRepositorio rolRepositorio;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepositorio.findByRolNombre(rolNombre);
    }
}