package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.modelo.Habilidad;
import ar.com.portfolioweb.backendspringboot.repositorio.HabilidadRepositorio;

@Service
@Transactional
public class HabilidadServicio {
    
    @Autowired
    HabilidadRepositorio habilidadRepositorio;

    public Optional<Habilidad> obtenerHabilidadPorNombre(String nombreHabilidad) {
        return habilidadRepositorio.findByNombreHabilidad(nombreHabilidad);
    }

    public Optional<Habilidad> obtenerHabilidadPorId(Integer id) {
        return habilidadRepositorio.findById(id);
    }

    public Set<Habilidad> obtenerTodasLasHabilidades() {
        Set<Habilidad> lista = habilidadRepositorio.findAll();
        return lista;
    }

    public List<Habilidad> obtenerPorCategoriaHabilidadId(Integer id) {
        return habilidadRepositorio.findByCategoriaHabilidadId(id);
    }
}
