package ar.com.portfolioweb.backendspringboot.servicio;


import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.modelo.TipoEducacion;
import ar.com.portfolioweb.backendspringboot.repositorio.TipoEducacionRepositorio;


@Service
@Transactional
public class TipoEducacionServicio {

    @Autowired
    TipoEducacionRepositorio tipoEducacionRepositorio;

    public Set<TipoEducacion> obtenerTodos() {
        Set<TipoEducacion> lista = tipoEducacionRepositorio.findAll();
        return lista;
    }
    
    public Optional<TipoEducacion> obtenerTipoEducacionPorNombre(String nombreTipoEducacion) {
        return tipoEducacionRepositorio.findByNombreTipoEducacion(nombreTipoEducacion);
    }

    public Optional<TipoEducacion> obtenerTipoEducacionPorId(Integer id) {
        return tipoEducacionRepositorio.findById(id);
    }

}