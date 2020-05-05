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
    @DisplayName("Test case A")
    void registerDeveloperDataSetA() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("");
        });

    }

    @Test
    @DisplayName("Test case B1")
    void registerDeveloperDataSetB1() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("213213123");
        });
    }

    @Test
    @DisplayName("Test case B2")
    void registerDeveloperDataSetB2() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("-112321");
        });
    }

    @Test
    @DisplayName("Test case B3")
    void registerDeveloperDataSetB3() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.setActiveDeveloper("!¤%%%!½½§§½");
        });
    }

    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
            app.setActiveDeveloper(developer);
            assertNotNull(app.getActiveDeveloper());

    }
}
