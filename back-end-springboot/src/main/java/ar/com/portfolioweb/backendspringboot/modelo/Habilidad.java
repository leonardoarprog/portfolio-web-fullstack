package ar.com.portfolioweb.backendspringboot.modelo;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreHabilidad;

  @ManyToOne
    @JoinColumn(name = "categoria_habilidad_id")
    @JsonIgnore
     private CategoriaHabilidad categoriaHabilidad;


     @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL)
     @JsonManagedReference
    @JsonIgnore
     private Set<PersonaHabilidad> personaHabilidades = new HashSet<>();
 
    

    public Habilidad() {
    }

    public Habilidad(String nombreHabilidad) {

        this.nombreHabilidad = nombreHabilidad;

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreHabilidad() {
        return this.nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

  public CategoriaHabilidad getCategoriaHabilidad() {
        return this.categoriaHabilidad;
    }

    public void setCategoriaHabilidad(CategoriaHabilidad categoriaHabilidad) {
        this.categoriaHabilidad = categoriaHabilidad;
    } 

}
