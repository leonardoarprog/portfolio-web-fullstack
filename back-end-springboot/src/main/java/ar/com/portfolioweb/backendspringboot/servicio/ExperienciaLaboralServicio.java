package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.ExperienciaLaboral;
import ar.com.portfolioweb.backendspringboot.repositorio.ExperienciaLaboralRepositorio;

@Service
@Transactional
public class ExperienciaLaboralServicio {

    @Autowired
    ExperienciaLaboralRepositorio experienciaLaboralRepositorio;

    public Set<ExperienciaLaboral> obtenerTodasLasExperienciasLaborales() {
        Set<ExperienciaLaboral> lista = experienciaLaboralRepositorio.findAll();
        return lista;
    }

    public Optional<ExperienciaLaboral> obtenerExperienciaLaboralPorId(Integer id) {
        return experienciaLaboralRepositorio.findById(id);
    }

    public void guardarExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {

        experienciaLaboralRepositorio.save(experienciaLaboral);
    }

    public void eliminarExperienciaLaboralPorId(Integer id) {
        experienciaLaboralRepositorio.deleteById(id);
    }

    public boolean existeExperienciaLaboralPorId(Integer id) {
        return experienciaLaboralRepositorio.existsById(id);
    }

    public List<ExperienciaLaboral> obtenerPorPersonaId(Integer id) {
        return experienciaLaboralRepositorio.findByPersonaId(id);
    }
    
}
