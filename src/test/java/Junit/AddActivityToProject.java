package Junit;

import domain.Activity;
import domain.Developer;
import domain.Project;
import System.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Add activity to project")
public class AddActivityToProject {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Codebreaker");
    private final Activity activity = new Activity("Coding");

    // Test input for names
    @Test
    @DisplayName("Test case A")
    void registerDeveloperDataSetA() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity(""), "");
        });
    }

    @Test
    @DisplayName("Test case B1")
    void registerDeveloperDataSetB1() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity(""), "39291");
        });
    }

    @Test
    @DisplayName("Test case B2")
    void registerDeveloperDataSetB2() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity("123213"), "");
        });
    }

    @Test
    @DisplayName("Test case B3")
    void registerDeveloperDataSetB3() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(activity, "");
        });
    }

    @Test
    @DisplayName("Test case B4")
    void registerDeveloperDataSetB4() {
        app.registerProject(project);
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(activity,project.getID());

        });
    }

    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        app.registerProject(project);
        app.registerActivityToProject(activity,project.getID());
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(activity,project.getID());
        });
    }

    @Test
    @DisplayName("Test case D")
    void registerDeveloperDataSetD() {
        app.registerProject(project);
        app.registerActivityToProject(activity,project.getID());
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(activity,project.getID());
        });
    }

    @Test
    @DisplayName("Test case E")
    void registerDeveloperDataSetE() {
       app.registerProject(project);
       app.registerActivityToProject(activity,project.getID());
       assertEquals(1,app.getProjectHM().get(project.getID()).getActivityList().size());
    }

    @Test
    @DisplayName("Test case F")
    void registerDeveloperDataSetF() throws Exception {
    }



}
