package ar.com.portfolioweb.backendspringboot.modelo;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ar.com.portfolioweb.backendspringboot.serializer.PersonaHabilidadSerializer;

@Entity
@DynamicUpdate
@Table
@JsonSerialize(using = PersonaHabilidadSerializer.class)
public class PersonaHabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "persona_id")
    @JsonIgnore
    private Persona persona;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "habilidad_id")
    private Habilidad habilidad;

    @Column(name="score",updatable = false, nullable = true)
    private Integer score;


    public PersonaHabilidad() {
    }


    public PersonaHabilidad(Persona persona, Habilidad habilidad, Integer score) {

        this.persona = persona;
        this.habilidad = habilidad;
        this.score = score;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Habilidad getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public String[] split(String string) {
        return null;
    }

}