package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.ExperienciaLaboral;

@Repository
public interface ExperienciaLaboralRepositorio extends CrudRepository<ExperienciaLaboral, Integer> {

    Optional<ExperienciaLaboral> findById(Integer id);

    boolean existsById(Integer id);

    Set<ExperienciaLaboral> findAll();

    List<ExperienciaLaboral> findByPersonaId(@Param("id") Integer id);

}