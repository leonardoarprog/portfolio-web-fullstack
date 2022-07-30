package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Pais;

@Repository
public interface PaisRepositorio extends CrudRepository<Pais, Integer>{

    List<Pais> findAll();
    
}