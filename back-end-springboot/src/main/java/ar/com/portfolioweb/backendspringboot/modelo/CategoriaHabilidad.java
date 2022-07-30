package ar.com.portfolioweb.backendspringboot.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class CategoriaHabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreCategoria;

  @JsonIgnore
    @OneToMany(mappedBy = "categoriaHabilidad")
    private Set<Habilidad> habilidad;
    public CategoriaHabilidad() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

 public Set<Habilidad> getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(Set<Habilidad> habilidad) {
        this.habilidad = habilidad;
    }

}
