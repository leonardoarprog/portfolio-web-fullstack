package ar.com.portfolioweb.backendspringboot.dto;

public class PaisDto {

    private Integer id;
    private String nombrePais;
    

    public PaisDto() {
    }

    public PaisDto(Integer id, String nombrePais) {
        this.id = id;
        this.nombrePais = nombrePais;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePais() {
        return this.nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }


}
