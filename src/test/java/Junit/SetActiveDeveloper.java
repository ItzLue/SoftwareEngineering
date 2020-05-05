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

    // no input
    @Test
    @DisplayName("Test case A")
    void registerDeveloperDataSetA() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("");
        });
        String expectedMessage = "Invalid ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test case B1")
    void registerDeveloperDataSetB1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("21387");
        });
        String expectedMessage = "Invalid ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test case B2")
    void registerDeveloperDataSetB2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("-112321");
        });
        String expectedMessage = "Invalid ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    @DisplayName("Test case B3")
    void registerDeveloperDataSetB3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("!¤%%%!½½§§½");
        });
        String expectedMessage = "Invalid ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        app.setActiveDeveloper(developer);
        assertNotNull(app.getActiveDeveloper());

    }
}
