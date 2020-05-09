package Junit;

import domain.Activity;
import domain.Developer;
import domain.Project;
import System.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Add activity to project")
public class RegisterActivityToProject {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Codebreaker");
    private final Activity activity = new Activity("Coding");
    private final Activity activity2 = new Activity("Coding");

    @Test
    @DisplayName("Test failing asserts")
    void testFailingAsserts() throws IllegalAccessException {
        app.registerProject(project);
        app.registerActivityToProject(activity,project.getID());
        System.out.println(project.getActivity(activity.getName()));
    }


    // No input for names
    @Test
    @DisplayName("Test case A")
    void registerActivityDataSetA() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity(""), "");
        });
        String expectedMessage = "Activity names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // No input for activity name and wrong project ID
    @Test
    @DisplayName("Test case B")
    void registerActivityDataSetB() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(new Activity(""), "39291");
        });
        String expectedMessage = "Activity names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Activity name already exists in project
    @Test
    @DisplayName("Test case C1")
    void registerActivityDataSetC1() throws IllegalAccessException {
        app.registerProject(project);
        app.registerActivityToProject(activity, project.getID());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerActivityToProject(activity2, project.getID());
        });
        String expectedMessage = "Not a valid name";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Activity with same name is not in project
    @Test
    @DisplayName("Test case C2")
    void registerActivityDataSetC2() {
        assertFalse(project.activityExists(activity.getName()));
    }

    // Add activity to project
    @Test
    @DisplayName("Test case C3")
    void registerActivityDataSetD3() {
        project.addActivity(activity);

    }

    // Project is initialized - but a developer who is not project leader tries to set activity date
    @Test
    @DisplayName("Test case D1")
    void registerActivityDataSetD1() throws IllegalAccessException {
        app.registerProject(project);
        app.registerDeveloper(developer);
        app.registerDeveloper(new Developer("Ole","Hansen"));
        app.registerActivityToProject(activity, project.getID());
        app.setProjectLeader(project.getID(),developer.getID());
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
           app.setActivityDate(true,project.getID(),activity.getName(),2020,20);
        });
        String expectedMessage = "You don't have access";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Developer is added twice to the same activity
    @Test
    @DisplayName("Test case D2")
    void registerActivityDataSetD2() throws IllegalAccessException {
        app.registerProject(project);
        app.registerDeveloper(developer);
        app.registerActivityToProject(activity,project.getID());
      app.setDeveloperToActivity(activity.getName(),project.getID(),developer.getID());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.setDeveloperToActivity(activity.getName(),project.getID(),developer.getID());
        });
        String expectedMessage = "The developer is already assigned to this activity";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Activity is added correctly and the activity size is 1 at the given project
    @Test
    @DisplayName("Test case E")
    void registerActivityDataSetE() throws IllegalAccessException {
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
        assertNull(project.getActivity(activity.getName()));
        assertNull(project.getProjectLeader());
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
        app.setActiveDeveloper(developer);
        app.registerActivityToProject(activity,project.getID());
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).addDeveloper(developer);
        app.setPlannedHoursForActivity(activity.getName(),project.getID(),50);
        activity.getInterval().setStartDate(2020,29);
        app.setWorkedHoursForActivity(activity.getName(),project.getID(),20);
        assertEquals(20,project.getActivity(activity.getName()).getWorkedHours());

    }
}
