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

    // No input
    @Test
    @DisplayName("Test case A")
    void registerDeveloperDataSetA() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("", ""));
        });
        String expectedMessage = "Developer names must be 2 letters or larger and can only contain alphabetic letters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Numbers as input
    @Test
    @DisplayName("Test case B1")
    void registerDeveloperDataSetB1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("123", "123"));
        });
        String expectedMessage = "Developer names must be 2 letters or larger and can only contain alphabetic letters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // String and number as input
    @Test
    @DisplayName("Test case B2")
    void registerDeveloperDataSetB2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("Jane", "123"));
        });
        String expectedMessage = "Developer names must be 2 letters or larger and can only contain alphabetic letters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Numer and string as input
    @Test
    @DisplayName("Test case B3")
    void registerDeveloperDataSetB3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("123", "Doe"));
        });
        String expectedMessage = "Developer names must be 2 letters or larger and can only contain alphabetic letters";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Signs as input
    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerDeveloper(new Developer("!#!#", "!#!#!"));
        });
        String expectedMessage = "Developer names must be 2 letters or larger and can only contain alphabetic letters";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test case D")
    void registerDeveloperDataSetD() {
        app.registerDeveloper(developer);
        assertEquals("Jane", app.developerHM.get(developer.getID()).getFirstName());
    }

    // Add over 9 developers
    @Test
    @DisplayName("Test case E")
    void registerDeveloperDataSetE() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        app.registerDeveloper(new Developer("Ole","Hansen"));
        app.registerDeveloper(new Developer("Hans","Hansen"));
        app.registerDeveloper(new Developer("Hans","Hansen"));
        app.registerDeveloper(new Developer("Hans","Hansen"));
        app.registerDeveloper(new Developer("Hans","Hansen"));

        assertAll("developer",
                () -> assertEquals("Ole",app.getDeveloperHM().get("OLHA01").getFirstName()),
                () -> assertEquals("Hans", app.getDeveloperHM().get("HAHA02").getFirstName())
        );
    }
}