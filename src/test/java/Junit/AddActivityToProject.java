package Junit;

import domain.Activity;
import domain.Developer;
import domain.Project;
import System.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Add activity to project")
public class AddActivityToProject {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Codebreaker");
    private final Activity activity = new Activity("Coding");

    // No input for names
    @Test
    @DisplayName("Test case A")
    void registerActivityDataSetA() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity(""), "");
        });
    }

    // No input for activity name and wrong project ID
    @Test
    @DisplayName("Test case B1")
    void registerActivityDataSetB1() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity(""), "39291");
        });
    }

    // Wrong name for activity and no project ID
    @Test
    @DisplayName("Test case B2")
    void registerActivityDataSetB2() {
        assertThrows(NullPointerException.class, () -> {
            app.registerActivityToProject(new Activity("123213"), "");
        });
    }

    // Valid activity and no project ID
    @Test
    @DisplayName("Test case B3")
    void registerActivityDataSetB3() {
        assertThrows(NullPointerException.class, () -> {
            app.registerActivityToProject(activity, "");
        });
    }

    // Valid project ID and valid activity
    @Test
    @DisplayName("Test case C")
    void registerActivityDataSetC() {
        app.registerProject(project);
        app.registerActivityToProject(activity, project.getID());
        String expectedMessage = "name: 'Coding', Start date: Week: 'null', Year: 'null', plannedHours: 0.0, workedHours: 0.0";
        String actualMessage =  project.getActivity("Coding").toString();
        assertEquals(expectedMessage,actualMessage);
    }

    // Activity name already exists in project
    @Test
    @DisplayName("Test case D1")
    void registerActivityDataSetD1() {
        app.registerProject(project);
        app.registerActivityToProject(activity, project.getID());
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            app.registerActivityToProject(activity, project.getID());
        });
        String expectedMessage = "Not a valid name";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Activity with same name is not in project
    @Test
    @DisplayName("Test case D2")
    void registerActivityDataSetD2() {
        assertFalse(project.activityExists(activity.getName()));
    }

    // Add activity to project
    @Test
    @DisplayName("Test case D3")
    void registerActivityDataSetD3() {
        project.addActivity(activity);

    }

    // Activity is added correctly and the activity size is 1 at the given project
    @Test
    @DisplayName("Test case E")
    void registerActivityDataSetE() {
        app.registerProject(project);
        app.registerActivityToProject(activity, project.getID());
        assertEquals(1, app.getProjectHM().get(project.getID()).getActivityList().size());
    }

    // No activities
    @Test
    @DisplayName("Test case G")
    void registerActivityDataSetG() {
        assertNull(project.getActivity(activity.getName()));
    }

    // No project leader and no activities
    @Test
    @DisplayName("Test case H1")
    void registerActivityDataSetH1() {

    }
    // Set planned hours
    @Test
    @DisplayName("Test case I1")
    void registerActivityDataSetI1() throws IllegalAccessException {
        app.registerProject(project);
        app.registerDeveloper(developer);
        app.registerActivityToProject(activity,project.getID());
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).addDeveloper(developer);
        app.setPlannedHoursForActivity(activity.getName(),project.getID(),50);

        assertEquals(50,app.getPlannedHoursForActivity(activity.getName(),project.getID()));
    }
    // Set worked hours
    @Test
    @DisplayName("Test case I2")
    void registerActivityDataSetI2() throws IllegalAccessException {
        app.registerProject(project);
        app.registerDeveloper(developer);
        app.registerActivityToProject(activity,project.getID());
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).addDeveloper(developer);
        app.setPlannedHoursForActivity(activity.getName(),project.getID(),50);
        app.setActiveDeveloper(developer.getID());
        app.setWorkedHoursForActivity(activity.getName(),project.getID(),20);
        assertEquals(20,app.getProjectHM().get(project.getID()).getActivity(activity.getName()).getWorkedHours());

    }

}
