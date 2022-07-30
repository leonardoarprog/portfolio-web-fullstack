package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.modelo.CategoriaHabilidad;
import ar.com.portfolioweb.backendspringboot.repositorio.CategoriaHabilidadRepositorio;


@Service
@Transactional
public class CategoriaHabilidadServicio {

    @Autowired
    CategoriaHabilidadRepositorio categoriaHabilidadRepositorio;

    public Set<CategoriaHabilidad> obtenerTodos() {
        Set<CategoriaHabilidad> lista = categoriaHabilidadRepositorio.findAll();
        return lista;
    }
    
    public Optional<CategoriaHabilidad> obtenerCategoriaHabilidadPorNombre(String nombreCategoria) {
        return categoriaHabilidadRepositorio.findByNombreCategoria(nombreCategoria);
    }

    public Optional<CategoriaHabilidad> obtenerCategoriaHabilidadPorId(Integer id) {
        return categoriaHabilidadRepositorio.findById(id);
    }

}
