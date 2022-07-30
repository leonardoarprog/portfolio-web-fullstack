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
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreProvincia;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    @JsonIgnore
    private Pais pais;

    @OneToMany(mappedBy = "provincia")
    @JsonIgnore
    private List<Localidad> localidades;

    public Provincia() {
    }

    public Provincia(Integer id, String nombreProvincia) {
        this.id = id;
        this.nombreProvincia = nombreProvincia;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return this.nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Localidad> getLocalidades() {
        return this.localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

}