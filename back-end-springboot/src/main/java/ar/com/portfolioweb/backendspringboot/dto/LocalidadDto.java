package ar.com.portfolioweb.backendspringboot.dto;

public class LocalidadDto {

    private Integer id;
    private String nombreLocalidad;
    private ProvinciaDto provincia;
    

    public LocalidadDto() {
    }


    public LocalidadDto(Integer id, String nombreLocalidad, ProvinciaDto provincia) {
        this.id = id;
        this.nombreLocalidad = nombreLocalidad;
        this.provincia = provincia;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreLocalidad() {
        return this.nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public ProvinciaDto getProvincia() {
        return this.provincia;
    }

    public void setProvincia(ProvinciaDto provincia) {
        this.provincia = provincia;
    }


}
