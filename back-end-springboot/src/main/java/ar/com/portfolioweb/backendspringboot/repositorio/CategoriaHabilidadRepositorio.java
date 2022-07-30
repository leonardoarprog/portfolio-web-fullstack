package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.CategoriaHabilidad;

@Repository
public interface CategoriaHabilidadRepositorio extends CrudRepository<CategoriaHabilidad, Integer> {

    Set<CategoriaHabilidad> findAll();
    Optional<CategoriaHabilidad> findByNombreCategoria(String nombreCategoria);
    Optional<CategoriaHabilidad> findById(Integer id);
}
