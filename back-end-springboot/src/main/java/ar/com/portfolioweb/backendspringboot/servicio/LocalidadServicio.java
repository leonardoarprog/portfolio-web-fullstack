package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Localidad;
import ar.com.portfolioweb.backendspringboot.repositorio.LocalidadRepositorio;

@Service
@Transactional
public class LocalidadServicio {

    @Autowired
    LocalidadRepositorio localidadRepositorio;

    public List<Localidad> obtenerPorProvinciaId(Integer id) {
        return localidadRepositorio.findByProvinciaId(id);
    }

    public Optional<Localidad> obtenerPorId(Integer id) {
        return localidadRepositorio.findById(id);
    }

    public Localidad obtenerPrimera() {
        return localidadRepositorio.findFirstBy();
    }
    
}
