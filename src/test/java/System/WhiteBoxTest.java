package System;

import domain.Activity;
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
    void registerProject() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        app.registerDeveloper(developer);
        app.registerProject(project);
        String projectID = "20191";
        String developerID = "JADO01";
        assertAll("project",
                () -> {
            assertNotNull(app.getProjectHM().get(projectID));
                    assertAll("project properties",
                            () -> assertEquals("20191", app.getProjectHM().get(projectID).getID()),
                            () -> assertEquals("Enigma Coding", app.getProjectHM().get(projectID).getName()),
                            () -> assertNull(app.projectHM.get(projectID).getInterval().getStartDate()),
                            () -> assertEquals(0, app.getProjectHM().get(projectID).getActivityList().size())
                    );
                },
                () -> {
            app.registerActivityToProject(new Activity("Coding"),projectID);
            app.setActivityDate(true,projectID,"Coding",2020,26);
            assertAll("Register activities",
                    () -> assertEquals(1,app.getProjectHM().get(projectID).getActivityList().size()),
                    () -> assertEquals("Coding",app.getProjectHM().get(projectID).getActivity("Coding").toString()),
                    () -> assertEquals(26,app.getProjectHM().get(projectID).getActivity("Coding").getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR)),
                    () -> assertEquals(2020,app.getProjectHM().get(projectID).getActivity("Coding").getInterval().getStartDate().get(Calendar.YEAR))
                    );
                },
                () -> {
                    app.setProjectLeader(projectID,developerID);
                    assertAll("Set project leader",
                            ()->assertNotNull(app.getProjectHM().get(projectID).getProjectLeader().getID())
                    );
                }
        );
    }

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