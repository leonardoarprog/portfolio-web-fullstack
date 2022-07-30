package ar.com.portfolioweb.backendspringboot.modelo;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
@Table
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tipo_educacion_id")
    private TipoEducacion tipoEducacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;


    private String nombreEstablecimiento;
    private Boolean enCursoActual;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private String tituloObtenido;
    private String descripcion;
    @Lob
    @Column(name = "isologo_establecimiento", updatable = true, nullable = true)
    private byte[] isologoEstablecimiento;


    public Educacion() {
    }

    public Educacion(TipoEducacion tipoEducacion, Persona persona, String nombreEstablecimiento, Boolean enCursoActual, LocalDate fechaIngreso, LocalDate fechaEgreso, String tituloObtenido, String descripcion, byte[] isologoEstablecimiento) {

        this.tipoEducacion = tipoEducacion;
        this.persona = persona;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.enCursoActual = enCursoActual;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.tituloObtenido = tituloObtenido;
        this.descripcion = descripcion;
        this.isologoEstablecimiento = isologoEstablecimiento;
    }
   

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoEducacion getTipoEducacion() {
        return this.tipoEducacion;
    }

    public void setTipoEducacion(TipoEducacion tipoEducacion) {
        this.tipoEducacion = tipoEducacion;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNombreEstablecimiento() {
        return this.nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public Boolean isEnCursoActual() {
        return this.enCursoActual;
    }

    public Boolean getEnCursoActual() {
        return this.enCursoActual;
    }

    public void setEnCursoActual(Boolean enCursoActual) {
        this.enCursoActual = enCursoActual;
    }

    public LocalDate getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return this.fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getTituloObtenido() {
        return this.tituloObtenido;
    }

    public void setTituloObtenido(String tituloObtenido) {
        this.tituloObtenido = tituloObtenido;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getIsologoEstablecimiento() {
        return this.isologoEstablecimiento;
    }

    public void setIsologoEstablecimiento(byte[] isologoEstablecimiento) {
        this.isologoEstablecimiento = isologoEstablecimiento;
    }
 

}