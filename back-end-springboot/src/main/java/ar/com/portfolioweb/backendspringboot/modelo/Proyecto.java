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
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private String nombreProyecto;
    private LocalDate fechaRealizacion;
    private String descripcion;
    private String urlProyecto;

    @Lob
    @Column(name = "img_proyecto_1", updatable = true, nullable = true)
    private byte[] imgProyecto1;

    @Lob
    @Column(name = "img_proyecto_2", updatable = true, nullable = true)
    private byte[] imgProyecto2;

    @Lob
    @Column(name = "img_proyecto_3", updatable = true, nullable = true)
    private byte[] imgProyecto3;

    public Proyecto() {
    }


    public Proyecto( Persona persona, String nombreProyecto, LocalDate fechaRealizacion, String descripcion, String urlProyecto, byte[] imgProyecto1, byte[] imgProyecto2, byte[] imgProyecto3) {

        this.persona = persona;
        this.nombreProyecto = nombreProyecto;
        this.fechaRealizacion = fechaRealizacion;
        this.descripcion = descripcion;
        this.urlProyecto = urlProyecto;
        this.imgProyecto1 = imgProyecto1;
        this.imgProyecto2 = imgProyecto2;
        this.imgProyecto3 = imgProyecto3;
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

    public String getNombreProyecto() {
        return this.nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public LocalDate getFechaRealizacion() {
        return this.fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlProyecto() {
        return this.urlProyecto;
    }

    public void setUrlProyecto(String urlProyecto) {
        this.urlProyecto = urlProyecto;
    }

    public byte[] getImgProyecto1() {
        return this.imgProyecto1;
    }

    public void setImgProyecto1(byte[] imgProyecto1) {
        this.imgProyecto1 = imgProyecto1;
    }

    public byte[] getImgProyecto2() {
        return this.imgProyecto2;
    }

    public void setImgProyecto2(byte[] imgProyecto2) {
        this.imgProyecto2 = imgProyecto2;
    }

    public byte[] getImgProyecto3() {
        return this.imgProyecto3;
    }

    public void setImgProyecto3(byte[] imgProyecto3) {
        this.imgProyecto3 = imgProyecto3;
    }
    

}