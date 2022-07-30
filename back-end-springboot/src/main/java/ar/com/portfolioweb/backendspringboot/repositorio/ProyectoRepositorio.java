package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Proyecto;

@Repository
public interface ProyectoRepositorio extends CrudRepository<Proyecto, Integer> {

    Optional<Proyecto> findById(Integer id);

    boolean existsById(Integer id);

    Set<Proyecto> findAll();

    List<Proyecto> findByPersonaId(@Param("id") Integer id);

}