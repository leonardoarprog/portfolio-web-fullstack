package ar.com.portfolioweb.backendspringboot.dto;

public class ExperienciaLaboralDto {
    
   
    private Integer tipoEmpleo;
    private String nombreEmpresa;
    private Boolean esTrabajoActual;
    private CharSequence fechaIngreso;
    private CharSequence fechaEgreso;
    private String puesto;
    private String descripcion;

    public ExperienciaLaboralDto() {
    }

    public ExperienciaLaboralDto(Integer tipoEmpleo, String nombreEmpresa, Boolean esTrabajoActual,
    CharSequence fechaIngreso, CharSequence fechaEgreso, String puesto, String descripcion) {

        this.tipoEmpleo = tipoEmpleo;
        this.nombreEmpresa = nombreEmpresa;
        this.esTrabajoActual = esTrabajoActual;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.puesto = puesto;
        this.descripcion = descripcion;

    }


    public Integer getTipoEmpleo() {
        return this.tipoEmpleo;
    }

    public void setTipoEmpleo(Integer tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
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

}
