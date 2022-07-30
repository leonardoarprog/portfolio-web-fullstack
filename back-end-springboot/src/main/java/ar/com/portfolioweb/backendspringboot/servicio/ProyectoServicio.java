package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Proyecto;
import ar.com.portfolioweb.backendspringboot.repositorio.ProyectoRepositorio;


@Service
@Transactional
public class ProyectoServicio {

    @Autowired
    ProyectoRepositorio proyectoRepositorio;

    public Set<Proyecto> obtenerTodasLosProyectos() {
        Set<Proyecto> lista = proyectoRepositorio.findAll();
        return lista;
    }

    public Optional<Proyecto> obtenerProyectosPorId(Integer id) {
        return proyectoRepositorio.findById(id);
    }

    public void guardarProyecto(Proyecto proyecto) {

        proyectoRepositorio.save(proyecto);
    }

    public void eliminarProyectoPorId(Integer id) {
        proyectoRepositorio.deleteById(id);
    }

    public boolean existeProyectoPorId(Integer id) {
        return proyectoRepositorio.existsById(id);
    }

    public List<Proyecto> obtenerPorPersonaId(Integer id) {
        return proyectoRepositorio.findByPersonaId(id);
    }
    
}