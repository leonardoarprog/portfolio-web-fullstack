package ar.com.portfolioweb.backendspringboot.dto;

public class UbicacionDto {

    LocalidadDto localidad;
    ProvinciaDto provincia;
    PaisDto pais;

    public UbicacionDto() {
    }

    public LocalidadDto getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(LocalidadDto localidad) {
        this.localidad = localidad;
    }

    public ProvinciaDto getProvincia() {
        return this.provincia;
    }

    public void setProvincia(ProvinciaDto provincia) {
        this.provincia = provincia;
    }

    public PaisDto getPais() {
        return this.pais;
    }

    public void setPais(PaisDto pais) {
        this.pais = pais;
    }

}
