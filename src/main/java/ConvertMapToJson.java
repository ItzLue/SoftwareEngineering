import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DynamicTest;

import java.util.HashMap;
import java.util.Map;
import domain.Developer;
public class ConvertMapToJson {
    public static void main(String[] args) {

    }
    private HashMap<String, Developer> developers = new HashMap<String, Developer>();

    public void registerDeveloper(Developer developer) {
        String ID = "";
        developer.setId(ID);
        developers.put(developer.getID(),developer);
    }
    public void ConvertMapToJson(){

        ObjectMapper objectMapper = new ObjectMapper();
        registerDeveloper(new Developer("Hans","Hansen"));
        registerDeveloper(new Developer("Lars","Hansen"));
        registerDeveloper(new Developer("Ole","Hansen"));

        try{
            Object value;
            String json = objectMapper.writeValueAsString(developers);
            System.out.println(json);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
