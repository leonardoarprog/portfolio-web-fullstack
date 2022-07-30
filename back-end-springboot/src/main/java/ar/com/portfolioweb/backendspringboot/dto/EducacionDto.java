package ar.com.portfolioweb.backendspringboot.dto;

public class EducacionDto {
    
    private Integer tipoEducacion;
    private String nombreEstablecimiento;
    private Boolean enCursoActual;
    private CharSequence fechaIngreso;
    private CharSequence fechaEgreso;
    private String tituloObtenido;
    private String descripcion;


    public EducacionDto() {
    }
   

    public EducacionDto(Integer tipoEducacion, String nombreEstablecimiento, Boolean enCursoActual, CharSequence fechaIngreso, CharSequence fechaEgreso, String tituloObtenido, String descripcion) {
        this.tipoEducacion = tipoEducacion;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.enCursoActual = enCursoActual;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.tituloObtenido = tituloObtenido;
        this.descripcion = descripcion;
    }


    public Integer getTipoEducacion() {
        return this.tipoEducacion;
    }

    public void setTipoEducacion(Integer tipoEducacion) {
        this.tipoEducacion = tipoEducacion;
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

    public CharSequence getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(CharSequence fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CharSequence getFechaEgreso() {
        return this.fechaEgreso;
    }

    public void setFechaEgreso(CharSequence fechaEgreso) {
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

}
