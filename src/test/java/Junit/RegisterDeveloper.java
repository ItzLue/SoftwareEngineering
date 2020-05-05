package Junit;

import domain.Developer;
import System.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Register Developer")
class RegisterDeveloper {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");

    @Test
    @DisplayName("Test case A")
    void registerDeveloperDataSetA() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("", ""));
        });

    }

    @Test
    @DisplayName("Test case B1")
    void registerDeveloperDataSetB1() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("123", "123"));
        });
    }

    @Test
    @DisplayName("Test case B2")
    void registerDeveloperDataSetB2() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("Jane", "123"));
        });
    }

    @Test
    @DisplayName("Test case B3")
    void registerDeveloperDataSetB3() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("123", "Doe"));
        });
    }

    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("!#!#", "!#!#!"));
        });
    }

    @Test
    @DisplayName("Test case D")
    void registerDeveloperDataSetD() {
        app.registerDeveloper(developer);
        assertEquals("Jane", app.developerHM.get(developer.getID()).getFirstName());
    }
}