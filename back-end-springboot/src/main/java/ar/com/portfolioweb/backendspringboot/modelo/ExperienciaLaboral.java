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
public class ExperienciaLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tipo_empleo_id")
    private TipoEmpleo tipoEmpleo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;
    private String nombreEmpresa;
    private Boolean esTrabajoActual;

    
    private LocalDate fechaIngreso;

    
    private LocalDate fechaEgreso;
    private String puesto;
    private String descripcion;
    @Lob
    @Column(name = "isologo_empresa", updatable = true, nullable = true)
    private byte[] isologoEmpresa;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(TipoEmpleo tipoEmpleo, String nombreEmpresa, Boolean esTrabajoActual,
            LocalDate fechaIngreso, LocalDate fechaEgreso, String puesto, String descripcion,
            byte[] isologoEmpresa) {

        this.tipoEmpleo = tipoEmpleo;
        this.nombreEmpresa = nombreEmpresa;
        this.esTrabajoActual = esTrabajoActual;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.isologoEmpresa = isologoEmpresa;

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoEmpleo getTipoEmpleo() {
        return this.tipoEmpleo;
    }

    public void setTipoEmpleo(TipoEmpleo tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Boolean isEsTrabajoActual() {
        return this.esTrabajoActual;
    }

    public Boolean getEsTrabajoActual() {
        return this.esTrabajoActual;
    }

    public void setEsTrabajoActual(Boolean esTrabajoActual) {
        this.esTrabajoActual = esTrabajoActual;
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

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getIsologoEmpresa() {
        return this.isologoEmpresa;
    }

    public void setIsologoEmpresa(byte[] isologoEmpresa) {
        this.isologoEmpresa = isologoEmpresa;
    }

}