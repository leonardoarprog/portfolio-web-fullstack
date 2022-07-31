package ar.com.portfolioweb.backendspringboot.servicio;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.portfolioweb.backendspringboot.modelo.Persona;
import ar.com.portfolioweb.backendspringboot.repositorio.PersonaRepositorio;

@Service
@Transactional
public class PersonaServicio {

    @Autowired
    PersonaRepositorio personaRepositorio;

    public List<Persona> obtenerTodos() {
        List<Persona> lista = personaRepositorio.findAll();
        return lista;
    }


    public Optional<Persona> obtenerPorId(Integer id) {
        return personaRepositorio.findById(id);
    }

    public Optional<Persona> obtenerPorNombre(String nombre) {
        return personaRepositorio.findByNombre(nombre);
    }

    public Optional<Persona> obtenerPorApellido(String apellido) {
        return personaRepositorio.findByApellido(apellido);
    }

    public void guardarPersona(Persona persona) {
        personaRepositorio.save(persona);
    }

    public boolean existePorId(Integer id) {
        return personaRepositorio.existsById(id);
    }

    public void actualizarFotoPerfil(Integer id, byte[] fotoPerfil) {
        personaRepositorio.updateFotoPerfil(id, fotoPerfil);
    }

    public void actualizarImgBg(Integer id, byte[] imgBg) {
        personaRepositorio.updateImgBg(id, imgBg);
    }

    public void actualizarsobreMi(Integer id, String sobreMi) {
        personaRepositorio.updateSobreMi(id, sobreMi);
    }

    public void borrarSobreMi(Integer id) {
        personaRepositorio.deleteSobreMi(id);
    }
    
    
    
}