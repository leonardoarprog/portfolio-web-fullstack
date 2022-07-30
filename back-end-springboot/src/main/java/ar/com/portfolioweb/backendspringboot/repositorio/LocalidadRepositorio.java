package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Localidad;

 
@Repository
public interface LocalidadRepositorio extends CrudRepository<Localidad, Integer> {

    List<Localidad> findByProvinciaId(@Param("id") Integer id);
    Optional<Localidad> findById(Integer id);
    Localidad findFirstBy();
    
}
