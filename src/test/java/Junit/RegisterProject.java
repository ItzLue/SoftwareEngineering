package Junit;

import domain.Developer;
import domain.Project;
import System.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Register Project")
public class RegisterProject {
    private final App app = new App();
    private final Project project = new Project("Enigma Codebreaker");
    private final Developer developer = new Developer("Jane","Doe");

    @Test
    @DisplayName("Test Failing Asserts")
    public void testFailingAsserts() {
       app.registerProject(project);
        System.out.println(app.getProjectHM().get(project.getID()).getName());
    }

    // No input
    @Test
    @DisplayName("Test case A")
    public void registerProjectDataSetA() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.registerProject(new Project("")));
        String expectedMessage = "Project names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Project name less than one letter
    @Test
    @DisplayName("Test case B")
    public void registerProjectDataSetB() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> app.registerProject(new Project("1")));
        String expectedMessage = "Project names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
    // Get activity size
    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        app.registerProject(project);
        assertEquals(0, app.getProjectHM().get(project.getID()).getActivityList().size());
    }

    // Get project name and ID
    @Test
    @DisplayName("Test case D")
    void registerDeveloperDataSetD() {
        app.registerProject(project);
        String projectID = project.getID();
        assertEquals(projectID, projectID);
        assertEquals("Enigma Codebreaker", app.getProjectHM().get(projectID).getName());
    }

    // Change project name
    @Test
    @DisplayName("Test case E1")
    void registerDeveloperDataSetE1() {
        app.registerProject(project);
        assertAll("make project",
                () -> {
                    assertEquals("Enigma Codebreaker", app.getProjectHM().get(project.getID()).getName());
                    app.setProjectName(project.getID(), "Minecraft");
                    assertAll("Change name",
                            () -> assertEquals("Minecraft", app.getProjectHM().get(project.getID()).getName()),
                            () -> assertEquals(project.getID(), app.getProjectHM().get(project.getID()).getID())
                    );
                }
        );
    }

    // Change name - project does not exist
    @Test
    @DisplayName("Test case E2")
    void registerDeveloperDataSetE2() {
        app.registerDeveloper(developer);
        Exception exception = assertThrows(NullPointerException.class, () -> app.setProjectName(project.getID(),"Minecraft"));
        String expectedMessage = "Project doesn't exist";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    @DisplayName("Test case E3")
    void registerDeveloperDataSetE3() {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setProjectLeader(project.getID(),developer.getID());
        app.registerDeveloper(new Developer("Ole","Hansen"));
        app.setActiveDeveloper("OLHA02");
        Exception exception = assertThrows(IllegalAccessException.class, () -> app.setProjectName(project.getID(),"helloWorld"));
        String expectedMessage = "Only the project leader can change the name of an initialized project";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Invalid end date
    @Test
    @DisplayName("Test case F")
    void registerDeveloperDataSetF() {
        app.registerProject(project);
        project.setProjectStartDate(2020, 29);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> project.setProjectEndDate(2019, 30));
        String expectedMessage = "Invalid date";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Add over 10 projects
    @Test
    @DisplayName("Test case G")
    void registerDeveloperDataSetG() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
         Project project1 = new Project("Enigma Codebreaker");
         Project project2 = new Project("Enigma Codebreaker");
         Project project3 = new Project("Enigma Codebreaker");
         Project project4 = new Project("Enigma Codebreaker");
         Project project5 = new Project("Enigma Codebreaker");
         Project project6 = new Project("Enigma Codebreaker");
         Project project7 = new Project("Enigma Codebreaker");
         Project project8 = new Project("Enigma Codebreaker");
         Project project9 = new Project("Enigma Codebreaker");
         Project project10 = new Project("Enigma Codebreaker");
         Project project11 = new Project("Enigma Codebreaker");
         Project project12 = new Project("Enigma Codebreaker");
         app.registerProject(project1);
         app.registerProject(project2);
         app.registerProject(project3);
         app.registerProject(project4);
         app.registerProject(project5);
         app.registerProject(project6);
         app.registerProject(project7);
         app.registerProject(project8);
         app.registerProject(project9);
         app.registerProject(project10);
         app.registerProject(project11);
         app.registerProject(project12);

        assertAll("project",
                () -> assertEquals(project1.getID(), app.getProjectHM().get(project1.getID()).getID()),
                () -> assertEquals(project2.getID(), app.getProjectHM().get(project2.getID()).getID()),
                () -> assertEquals(project3.getID(), app.getProjectHM().get(project3.getID()).getID()),
                () -> assertEquals(project4.getID(), app.getProjectHM().get(project4.getID()).getID()),
                () -> assertEquals(project5.getID(), app.getProjectHM().get(project5.getID()).getID()),
                () -> assertEquals(project6.getID(), app.getProjectHM().get(project6.getID()).getID()),
                () -> assertEquals(project7.getID(), app.getProjectHM().get(project7.getID()).getID()),
                () -> assertEquals(project8.getID(), app.getProjectHM().get(project8.getID()).getID()),
                () -> assertEquals(project9.getID(), app.getProjectHM().get(project9.getID()).getID()),
                () -> assertEquals(project10.getID(), app.getProjectHM().get(project10.getID()).getID()),
                () -> assertEquals(project11.getID(), app.getProjectHM().get(project11.getID()).getID()),
                () -> assertEquals(project12.getID(), app.getProjectHM().get(project12.getID()).getID())

        );
    }

}

