package ar.com.portfolioweb.backendspringboot.serializer;

import com.fasterxml.jackson.databind.JsonSerializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.SerializerProvider;

import ar.com.portfolioweb.backendspringboot.modelo.PersonaHabilidad;

public class PersonaHabilidadSerializer extends JsonSerializer<PersonaHabilidad> {

    @Override
    public void serialize(PersonaHabilidad personaHabilidad, JsonGenerator jsonGen,
            SerializerProvider serProv) throws IOException,
            JsonProcessingException {

        jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", personaHabilidad.getId());
        jsonGen.writeStringField("categoria_habilidad", personaHabilidad.getHabilidad().getCategoriaHabilidad().getNombreCategoria());
        jsonGen.writeObjectFieldStart("score");
        jsonGen.writeNumberField(personaHabilidad.getHabilidad().getNombreHabilidad().toString(), personaHabilidad.getScore());     
        jsonGen.writeEndObject();
        jsonGen.writeEndObject();

    }

}