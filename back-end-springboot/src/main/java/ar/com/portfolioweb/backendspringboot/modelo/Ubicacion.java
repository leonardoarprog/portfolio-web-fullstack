package ar.com.portfolioweb.backendspringboot.modelo;

public class Ubicacion {
    
    Localidad localidad;
    Provincia provincia;
    Pais pais;

    public Ubicacion() {
    }

    public Ubicacion(Localidad localidad, Provincia provincia, Pais pais) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
    }


    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Provincia getProvincia() {
        return this.provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
}
