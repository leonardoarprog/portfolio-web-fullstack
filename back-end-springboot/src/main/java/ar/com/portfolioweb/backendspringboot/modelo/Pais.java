package ar.com.portfolioweb.backendspringboot.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombrePais;

    @OneToMany(mappedBy = "pais")
    @JsonIgnore
    private List<Provincia> provincias;

    public Pais() {
    }

    public Pais(Integer id, String nombrePais) {
        this.id = id;
        this.nombrePais = nombrePais;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePais() {
        return this.nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

}
