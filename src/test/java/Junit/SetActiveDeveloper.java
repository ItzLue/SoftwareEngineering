package Junit;

import domain.Developer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import System.App;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Set active developer")
class SetActiveDeveloper {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");


    @Test
    @DisplayName("Test failing asserts")
    void testFailingAsserts(){
        app.registerDeveloper(developer);
        app.setActiveDeveloper(developer);
        System.out.println(app.getDeveloperHM().get(developer.getID()));
    }

    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        app.setActiveDeveloper(developer);
        assertNotNull(app.getActiveDeveloper());
    }

    @Test
    @DisplayName("Test case D")
    void registerDeveloperDataSetD() {
        app.registerDeveloper(developer);
        app.setActiveDeveloper(developer.getID());
        assertEquals(developer.getID(), app.getActiveDeveloper().getID());
    }
}
