package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Educacion;

@Repository
public interface EducacionRepositorio extends CrudRepository<Educacion, Integer> {

    Optional<Educacion> findById(Integer id);

    boolean existsById(Integer id);

    Set<Educacion> findAll();

    List<Educacion> findByPersonaId(@Param("id") Integer id);

}