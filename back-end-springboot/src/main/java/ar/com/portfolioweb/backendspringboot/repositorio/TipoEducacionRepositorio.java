package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.TipoEducacion;


@Repository
public interface TipoEducacionRepositorio extends CrudRepository<TipoEducacion, Integer> {

    Set<TipoEducacion> findAll();
    Optional<TipoEducacion> findByNombreTipoEducacion(String nombreTipoEducacion);
    Optional<TipoEducacion> findById(Integer id);
}