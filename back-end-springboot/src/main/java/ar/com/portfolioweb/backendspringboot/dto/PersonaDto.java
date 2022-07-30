package ar.com.portfolioweb.backendspringboot.dto;

import java.time.LocalDate;



public class PersonaDto {

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;
    private String direccion;
    private UbicacionDto ubicacion;
    private String posicionLaboral;
    private String sobreMi;
    private byte[] fotoPerfil;
    private byte[] imgBg;
    private String urlRepositorio;
    private String urlFacebook;
    private String urlTwitter;

    public PersonaDto() {
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

    public UbicacionDto getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(UbicacionDto ubicacion) {
        this.ubicacion = ubicacion;
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

    public void setUrlImgBg(byte[] imgBg) {
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