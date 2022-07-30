package ar.com.portfolioweb.backendspringboot.modelo;

import java.util.Set;

import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class TipoEducacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreTipoEducacion;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoEducacion")
    private Set<Educacion> educacion;


    public TipoEducacion() {
    }
   

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTipoEducacion() {
        return this.nombreTipoEducacion;
    }

    public void setNombreTipoEducacion(String nombreTipoEducacion) {
        this.nombreTipoEducacion = nombreTipoEducacion;
    }

    public Set<Educacion> getEducacion() {
        return this.educacion;
    }

    public void setEducacion(Set<Educacion> educacion) {
        this.educacion = educacion;
    }


}