package ar.com.portfolioweb.backendspringboot.seguridad.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.data.relational.core.mapping.Table;

import ar.com.portfolioweb.backendspringboot.seguridad.enums.RolNombre;


@Entity
@Table
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RolNombre rolNombre;

    public Rol() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return this.rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

}