package ar.com.portfolioweb.backendspringboot.dto;

public class ProvinciaDto {
    private Integer id;
    private String nombreProvincia;
    private PaisDto pais;


    public ProvinciaDto() {
    }


    public ProvinciaDto(Integer id, String nombreProvincia, PaisDto pais) {
        this.id = id;
        this.nombreProvincia = nombreProvincia;
        this.pais = pais;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return this.nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public PaisDto getPais() {
        return this.pais;
    }

    public void setPais(PaisDto pais) {
        this.pais = pais;
    }

}
