package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Provincia;

@Repository
public interface ProvinciaRepositorio extends CrudRepository<Provincia, Integer> {
    List<Provincia> findByPaisId(@Param("id") Integer id);
}
