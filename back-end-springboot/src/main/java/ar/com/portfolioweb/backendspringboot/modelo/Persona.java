package ar.com.portfolioweb.backendspringboot.modelo;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ar.com.portfolioweb.backendspringboot.seguridad.modelo.Usuario;
import ar.com.portfolioweb.backendspringboot.serializer.LocalidadSerializer;



@Entity
@DynamicUpdate
@Table
public class Persona {
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Usuario usuario;
    @Id
    @Column(name = "id")
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "localidad_id")
    @JsonProperty("ubicacion")
    @JsonSerialize(using = LocalidadSerializer.class)
    private Localidad localidad;
    private String posicionLaboral;
    @Column(name="sobre_mi",updatable = false, nullable = true)
    private String sobreMi;
    @Lob
    @Column(name="foto_perfil",updatable = false, nullable = true)
    @JsonIgnore
    private byte[] fotoPerfil;
    @Lob
    @Column(name="img_bg",updatable = false, nullable = true)
    @JsonIgnore
    private byte[] imgBg;
    private String urlRepositorio;
    private String urlFacebook;
    private String urlTwitter;

    public Persona() {
    }

    public Persona(Usuario usuario, String nombre, String apellido, LocalDate fechaNacimiento, String email,
            String direccion, Localidad localidad, String posicionLaboral,
            String sobreMi, byte[] fotoPerfil, byte[] imgBg, String urlRepositorio, String urlFacebook,
            String urlTwitter) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.direccion = direccion;
        this.localidad = localidad;
        this.posicionLaboral = posicionLaboral;
        this.sobreMi = sobreMi;
        this.fotoPerfil = fotoPerfil;
        this.imgBg = imgBg;
        this.urlRepositorio = urlRepositorio;
        this.urlFacebook = urlFacebook;
        this.urlTwitter = urlTwitter;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getPosicionLaboral() {
        return this.posicionLaboral;
    }

    public void setPosicionLaboral(String posicionLaboral) {
        this.posicionLaboral = posicionLaboral;
    }

    public String getSobreMi() {
        return this.sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public byte[] getFotoPerfil() {
        return this.fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public byte[] getImgBg() {
        return this.imgBg;
    }

    public void setImgBg(byte[] imgBg) {
        this.imgBg = imgBg;
    }

    public String getUrlRepositorio() {
        return this.urlRepositorio;
    }

    public void setUrlRepositorio(String urlRepositorio) {
        this.urlRepositorio = urlRepositorio;
    }

    public String getUrlFacebook() {
        return this.urlFacebook;
    }

    public void setUrlFacebook(String urlFacebook) {
        this.urlFacebook = urlFacebook;
    }

    public String getUrlTwitter() {
        return this.urlTwitter;
    }

    public void setUrlTwitter(String urlTwitter) {
        this.urlTwitter = urlTwitter;
    }

}