package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Pais;
import ar.com.portfolioweb.backendspringboot.repositorio.PaisRepositorio;

@Service
@Transactional
public class PaisServicio {

    @Autowired
    PaisRepositorio paisRepositorio;

    public List<Pais> obtenerTodos() {
        List<Pais> lista = paisRepositorio.findAll();
        return lista;
    }

}
