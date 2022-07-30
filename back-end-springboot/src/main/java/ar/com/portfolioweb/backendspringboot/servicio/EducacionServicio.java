package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Educacion;
import ar.com.portfolioweb.backendspringboot.repositorio.EducacionRepositorio;


@Service
@Transactional
public class EducacionServicio {

    @Autowired
    EducacionRepositorio educacionRepositorio;

    public Set<Educacion> obtenerTodasLasEducaciones() {
        Set<Educacion> lista = educacionRepositorio.findAll();
        return lista;
    }

    public Optional<Educacion> obtenerEducacionPorId(Integer id) {
        return educacionRepositorio.findById(id);
    }

    public void guardarEducacion(Educacion experienciaLaboral) {

        educacionRepositorio.save(experienciaLaboral);
    }

    public void eliminarEducacionPorId(Integer id) {
        educacionRepositorio.deleteById(id);
    }

    public boolean existeEducacionPorId(Integer id) {
        return educacionRepositorio.existsById(id);
    }

    public List<Educacion> obtenerPorPersonaId(Integer id) {
        return educacionRepositorio.findByPersonaId(id);
    }
    
}
