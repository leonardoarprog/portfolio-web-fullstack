package ar.com.portfolioweb.backendspringboot.modelo;

import lombok.Builder;


@Builder
public class Imagen {

    private Integer id;

    private String nombre;

    private String tipo;

    private byte[] imagen;

    public Imagen() {
    }

    public Imagen(Integer id, String nombre, String tipo, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getImagen() {
        return this.imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
