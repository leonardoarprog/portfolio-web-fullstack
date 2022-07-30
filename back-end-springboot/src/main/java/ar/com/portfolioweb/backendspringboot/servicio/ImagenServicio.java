package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Imagen;
import ar.com.portfolioweb.backendspringboot.repositorio.ImagenRepositorio;


@Service
@Transactional
public class ImagenServicio {

    @Autowired(required=false)
    ImagenRepositorio imagenRepositorio;

    public Optional<Imagen> obtenerImgPorNombre(String nombre) {
        return imagenRepositorio.findByNombre(nombre);
    }

    public Optional<Imagen> obtenerImgPorId(Integer id) {
        return imagenRepositorio.findById(id);
    }

    public void guardarImagen(Imagen imagen) {
        imagenRepositorio.save(imagen);
    }
    
}
