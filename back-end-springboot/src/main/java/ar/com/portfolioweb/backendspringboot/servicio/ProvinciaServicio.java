package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Provincia;
import ar.com.portfolioweb.backendspringboot.repositorio.ProvinciaRepositorio;

@Service
@Transactional
public class ProvinciaServicio {
    
    @Autowired
    ProvinciaRepositorio provinciaRepositorio;

    public List<Provincia> obtenerPorPaisId(Integer id) {
        return provinciaRepositorio.findByPaisId(id);
    }
}
