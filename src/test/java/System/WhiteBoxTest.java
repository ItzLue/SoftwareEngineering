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
import static org.junit.jupiter.api.Assertions.assertAll;

class WhiteBoxTest {

    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Coding");

    @Test
    void registerDeveloper() {
        String ID = app.makeDeveloperID(developer);
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

    @Test
    void registerProject() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.


        assertAll("properties",
                () -> {
                    app.registerDeveloper(developer);
                    app.registerProject(project);
                    String projectID = "20191";
                    String developerID = "JADO01";
                    assertAll("project properties",
                            () -> assertEquals("20191", app.getProjectHM().get(projectID).getID()),
                            () -> assertEquals("Enigma Coding", app.getProjectHM().get(projectID).getName()),
                            () -> assertNull(app.projectHM.get(projectID).getInterval().getStartDate()),
                            () -> assertEquals(0, app.getProjectHM().get(projectID).getActivityList().size())
                    );

                },
                () -> {

            assertAll("Register activities");
                }
        );
    }


//        assertAll("project",
//                () -> {
//                    assertNotNull(app.getProjectHM().get("20191"));
//
//                    // Executed only if the previous assertion is valid.
//                    assertAll("project name",
//                            () -> assertEquals("20191",app.projectHM.get("20191").getID()),
//                            () -> assertEquals("Enigma Coding", app.projectHM.get("20191").getName()),
//                            app.getProjectHM().get(projectID).getProjectLeader().getID());
//                },
//                assertAll("project leader",
//                        ()-> assertEquals(developerID,app.projectHM.get(projectID).getProjectLeader().getID()));
//    }


    @Test
    void setProjectLeader() {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setProjectLeader("20191", "JADO01");
        assertEquals("Jane", app.projectHM.get(project.getID()).getProjectLeader().getFirstName());
    }

    @Test
    void setActiveDeveloper() {
        app.setActiveDeveloper(developer);
        assertEquals("Jane", app.getActiveDeveloper().getFirstName());
    }

}