package ar.com.portfolioweb.backendspringboot.dto;

public class ProyectoDto {

    private String nombreProyecto;
    private CharSequence fechaRealizacion;
    private String descripcion;
    private String urlProyecto;


    public ProyectoDto() {
    }


    public ProyectoDto(String nombreProyecto, CharSequence fechaRealizacion, String descripcion, String urlProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.fechaRealizacion = fechaRealizacion;
        this.descripcion = descripcion;
        this.urlProyecto = urlProyecto;
    }


    public String getNombreProyecto() {
        return this.nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public CharSequence getFechaRealizacion() {
        return this.fechaRealizacion;
    }

    public void setFechaRealizacion(CharSequence fechaRealizacion) {
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

}
