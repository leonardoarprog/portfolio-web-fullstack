package ar.com.portfolioweb.backendspringboot.serializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ImagenSerializer extends StdSerializer<byte[]> {

    public ImagenSerializer(){
        super(byte[].class);
    }

    protected ImagenSerializer(Class<byte[]> t) {
        super(t);
    }

   
    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String jsonReadableString = new String(value, StandardCharsets.UTF_8);
        gen.writeString(jsonReadableString);
    }
     
}
