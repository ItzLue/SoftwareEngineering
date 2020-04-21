import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Developer;
import jdk.internal.org.objectweb.asm.TypeReference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonMappingWrite {

    public HashMap<String, Developer> developerHM = new HashMap<String, Developer>();
    public Developer developer;


    public static void main(String[] args) {

        new JsonMappingWrite();
        JsonMappingRead();

    }
    public void registerDeveloper(Developer developer) {
        String ID;
        if (developerHM.size() > 9) {
            ID = developer.getFirstName().substring(0,2).toLowerCase() + developer.getLastName().substring(0,2).toLowerCase() + (developerHM.size()+1);
        } else {
            ID = developer.getFirstName().substring(0,2).toLowerCase() + developer.getLastName().substring(0,2) .toLowerCase() + 0 + (developerHM.size()+1);
        }
        developer.setId(ID);
        developerHM.put(developer.getID(),developer);
    }
    public JsonMappingWrite(){
        ObjectMapper mapper = new ObjectMapper();

        registerDeveloper(new Developer("Hans","Hansen"));
        registerDeveloper(new Developer("Jens","Olsen"));
        registerDeveloper(new Developer("Jonas","Andersen"));
        registerDeveloper(new Developer("Hans","Mikkelsen"));
        try {
            mapper.writeValue(new File("devs.json"),developerHM);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void JsonMappingRead(){
        ObjectMapper mapper = new ObjectMapper();

      try {
          Map<String,Developer> developerMap = mapper.readValue(new File(
                  "result.json"), new TypeReference<Map<String,Object>>() {
          });

            System.out.println("Car : " + developerHM.get("jeol02"));
            System.out.println("Price : " + developerHM.get("joan03"));
            System.out.println("Model : " + developerHM.get("haha01"));
            System.out.println("Colors : " + developerHM.get("colors"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }

}
