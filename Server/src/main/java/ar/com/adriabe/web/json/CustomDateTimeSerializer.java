package ar.com.adriabe.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Mildo on 1/22/15.
 */
public class CustomDateTimeSerializer extends JsonSerializer<DateTime>{
    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(dateTime == null ){
            jsonGenerator.writeString("");
        }else{
            jsonGenerator.writeString(format.format(dateTime.toDate()));
        }


    }
}
