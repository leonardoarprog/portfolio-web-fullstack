package ar.com.portfolioweb.backendspringboot.servicio;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.portfolioweb.backendspringboot.modelo.TipoEmpleo;
import ar.com.portfolioweb.backendspringboot.repositorio.TipoEmpleoRepositorio;


@Service
@Transactional
public class TipoEmpleoServicio {

    @Autowired
    TipoEmpleoRepositorio tipoEmpleoRepositorio;

    public Set<TipoEmpleo> obtenerTodos() {
        Set<TipoEmpleo> lista = tipoEmpleoRepositorio.findAll();
        return lista;
    }
    
    public Optional<TipoEmpleo> obtenerTipoEmpleoPorNombre(String nombreTipoEmpleo) {
        return tipoEmpleoRepositorio.findByNombreTipoEmpleo(nombreTipoEmpleo);
    }

    public Optional<TipoEmpleo> obtenerTipoEmpleoPorId(Integer id) {
        return tipoEmpleoRepositorio.findById(id);
    }

}
