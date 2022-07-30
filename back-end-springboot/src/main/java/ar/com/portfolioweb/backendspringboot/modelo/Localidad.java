package ar.com.portfolioweb.backendspringboot.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreLocalidad;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    @JsonIgnore
    private Provincia provincia;

    @OneToMany(mappedBy = "localidad")
    @JsonIgnore
    private List<Persona> personas;

    public Localidad() {
    }

    public Localidad(Integer id, String nombreLocalidad) {
        this.id = id;
        this.nombreLocalidad = nombreLocalidad;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreLocalidad() {
        return this.nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public Provincia getProvincia() {
        return this.provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Persona> getPersonas() {
        return this.personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

}