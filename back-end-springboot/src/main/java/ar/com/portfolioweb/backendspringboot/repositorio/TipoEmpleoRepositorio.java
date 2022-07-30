package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.TipoEmpleo;

@Repository
public interface TipoEmpleoRepositorio extends CrudRepository<TipoEmpleo, Integer> {

    Set<TipoEmpleo> findAll();
    Optional<TipoEmpleo> findByNombreTipoEmpleo(String nombreTipoEmpleo);
    Optional<TipoEmpleo> findById(Integer id);
}