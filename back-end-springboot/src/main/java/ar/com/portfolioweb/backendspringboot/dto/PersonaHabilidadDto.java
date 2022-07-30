package ar.com.portfolioweb.backendspringboot.dto;

public class PersonaHabilidadDto {

   

    Integer habilidad;

    Integer score;



    public PersonaHabilidadDto() {
    }


    public PersonaHabilidadDto(Integer habilidad, Integer score) {
       
        this.habilidad = habilidad;
        this.score = score;
    }




    public Integer getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(Integer habilidad) {
        this.habilidad = habilidad;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }



   
}
