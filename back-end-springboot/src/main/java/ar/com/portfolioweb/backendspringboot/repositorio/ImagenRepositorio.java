package ar.com.portfolioweb.backendspringboot.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.portfolioweb.backendspringboot.modelo.Imagen;

@Repository
public interface ImagenRepositorio extends CrudRepository<Imagen, Integer> {
	Optional<Imagen> findByNombre(String nombre);
	Optional<Imagen> findById(Integer id);
}