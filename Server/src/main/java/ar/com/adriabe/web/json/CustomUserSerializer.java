package ar.com.adriabe.web.json;

import ar.com.adriabe.model.User;
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
public class CustomUserSerializer extends JsonSerializer<User>{
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(user == null ){
            jsonGenerator.writeString("-1|Anonymous");
        }else{
            jsonGenerator.writeString(user.getId()+"|"+user.getUsername());
        }


    }
}
