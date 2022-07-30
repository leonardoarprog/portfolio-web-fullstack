package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.com.portfolioweb.backendspringboot.modelo.Habilidad;

public interface HabilidadRepositorio extends CrudRepository<Habilidad, Integer> {

    Optional<Habilidad> findByNombreHabilidad(String nombreHabilidad);
    Set<Habilidad> findAll();
    Optional<Habilidad> findById(Integer id);
    List<Habilidad> findByCategoriaHabilidadId(@Param("id") Integer id);

}
