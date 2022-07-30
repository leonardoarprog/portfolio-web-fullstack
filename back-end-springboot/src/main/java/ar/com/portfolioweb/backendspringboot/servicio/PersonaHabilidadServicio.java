package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.modelo.PersonaHabilidad;
import ar.com.portfolioweb.backendspringboot.repositorio.PersonaHabilidadRepositorio;

@Service
@Transactional
public class PersonaHabilidadServicio {

    @Autowired
    PersonaHabilidadRepositorio personaHabilidadRepositorio;

    public Set<PersonaHabilidad> obtenerTodasLasPersonasHabilidades() {
        Set<PersonaHabilidad> lista = personaHabilidadRepositorio.findAll();
        return lista;
    }

    public Optional<PersonaHabilidad> obtenerPersonaHabilidadPorId(Integer id) {
        return personaHabilidadRepositorio.findById(id);
    }

    public void guardarPersonaHabilidad(PersonaHabilidad personaHabilidad) {

        personaHabilidadRepositorio.save(personaHabilidad);
    }

    public void eliminarPersonaHabilidadPorId(Integer id) {
        personaHabilidadRepositorio.deleteById(id);
    }

    public boolean existePersonaHabilidadPorId(Integer id) {
        return personaHabilidadRepositorio.existsById(id);
    }

    public List<PersonaHabilidad> obtenerPorPersonaId(Integer id) {
        return personaHabilidadRepositorio.findByPersonaId(id);
    }

    public void actualizarHabilidadScore(Integer id, Integer score) {
        personaHabilidadRepositorio.updateHabilidadScore(id, score);
    }

    public Optional<Integer> obtenerHabilidadScore(Integer id) {
        return personaHabilidadRepositorio.selectHabilidadScore(id);
    }

}