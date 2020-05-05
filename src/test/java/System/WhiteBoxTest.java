package System;

import domain.Developer;
import domain.Project;
import gherkin.lexer.De;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class WhiteBoxTest {

    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Coding");

    @Test
    void registerDeveloper() {
        app.registerDeveloper(developer);
        assertEquals("Jane", app.developerHM.get("JADO01").getFirstName());
    }

    @Test
    void groupedAssertions() {
        assertAll("developer",
                () -> assertEquals("Jane", developer.getFirstName()),
                () -> assertEquals("Doe", developer.getLastName())
        );
    }
// Test2

    @Test
    void registerProject() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("project",
                () -> {
                    app.registerProject(project);
                    assertNotNull(app.getProjectHM().get("20191"));

                    // Executed only if the previous assertion is valid.
                    assertAll("project name",
                            () -> assertEquals("20191",app.projectHM.get("20191").getID()),
                            () -> assertEquals("Enigma Coding", app.projectHM.get("20191").getName())
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    Developer projectLeader = app.getProjectHM().get("20191").getProjectLeader();
                    Exception exception = assertThrows(NullPointerException.class, () ->
                            app.getProjectHM().get("20191").getProjectLeader());
                    assertNull(exception.getMessage());
// Test

                    // Executed only if the previous assertion is valid.
                    assertAll("name",
                            () -> assertTrue(app.getActiveDeveloper().getFirstName().startsWith("J")),
                            () -> assertTrue(app.getActiveDeveloper().getLastName().startsWith("D"))
                    );
                }
        );
    }




//    @Test
//    void setProjectLeader() {
//        app.setProjectLeader("20191", "JADO01");
//        assertEquals("Jane", app.projectHM.get(project.getID()).getProjectLeader().getFirstName());
//    }

    @Test
    void setActiveDeveloper() {
        app.setActiveDeveloper(developer);
        assertEquals("Jane", app.getActiveDeveloper().getFirstName());
    }

}