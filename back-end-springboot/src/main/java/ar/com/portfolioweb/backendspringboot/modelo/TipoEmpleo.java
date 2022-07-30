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
public class TipoEmpleo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreTipoEmpleo;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoEmpleo")
    private Set<ExperienciaLaboral> experienciaLaboral;

    public TipoEmpleo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTipoEmpleo() {
        return this.nombreTipoEmpleo;
    }

    public void setNombreTipoEmpleo(String nombreTipoEmpleo) {
        this.nombreTipoEmpleo = nombreTipoEmpleo;
    }

    public Set<ExperienciaLaboral> getExperienciaLaboral() {
        return this.experienciaLaboral;
    }

    public void setExperienciaLaboral(Set<ExperienciaLaboral> experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

}