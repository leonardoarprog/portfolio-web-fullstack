package ar.com.portfolioweb.backendspringboot.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import ar.com.portfolioweb.backendspringboot.modelo.Localidad;

public class LocalidadSerializer extends JsonSerializer<Localidad> {

    @Override
    public void serialize(Localidad localidad, JsonGenerator jsonGen,
            SerializerProvider serProv) throws IOException,
            JsonProcessingException {
        jsonGen.writeStartObject();
        jsonGen.writeObjectFieldStart("localidad");
        jsonGen.writeNumberField("id", localidad.getId());
        jsonGen.writeStringField("nombreLocalidad", localidad.getNombreLocalidad());
        jsonGen.writeEndObject();
        jsonGen.writeObjectFieldStart("provincia");
        jsonGen.writeNumberField("id", localidad.getProvincia().getId());
        jsonGen.writeStringField("nombreProvincia", localidad.getProvincia().getNombreProvincia());
        jsonGen.writeEndObject();
        jsonGen.writeObjectFieldStart("pais");
        jsonGen.writeNumberField("id", localidad.getProvincia().getPais().getId());
        jsonGen.writeStringField("nombrePais", localidad.getProvincia().getPais().getNombrePais());
        jsonGen.writeEndObject();
        jsonGen.writeEndObject();

    }

}