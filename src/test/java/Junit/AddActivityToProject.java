package Junit;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import domain.Activity;
import domain.Developer;
import domain.Project;
import System.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

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
        assertEquals("name: 'Coding", project.getActivity("Coding").toString());
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

    // Set invalid end date
    @Test
    @DisplayName("Test case F")
    void registerActivityDataSetF() {
        app.registerProject(project);
        app.registerActivityToProject(activity, project.getID());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.setActivityDate(false, project.getID(), activity.getName(), 2020, 29);
        });
        String expectedMessage = "Invalid date";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

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
        String expectedMessage = "Name:'" + project.getName() + '\'' +
                ", ID: '" + project.getID() + '\'' +
                ", Project Leader: " + '\'' +
                null + '\''
                + ", Start date: " + '\'' +
                "Week: " + null +
                " Year: " +
                null +
                ", Activity list: []";
        String actualMessage = project.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    // Set planned hours
    @Test
    @DisplayName("Test case I1")
    void registerActivityDataSetI1() throws IllegalAccessException {
        app.registerProject(project);
        app.registerActivityToProject(activity,project.getID());
        app.setActivityDate(true,project.getID(),activity.getName(),2020,29);
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).setPlannedHours(20.0);
        double plannedHours = activity.getPlannedHours();
        assertEquals(20.0,plannedHours);
    }
    // Set worked hours
    @Test
    @DisplayName("Test case I2")
    void registerActivityDataSetI2() throws IllegalAccessException {

        app.registerProject(project);
        app.registerDeveloper(developer);
        app.registerActivityToProject(activity,project.getID());
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).addDeveloper(developer);
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).setPlannedHours(20);
        developer.setWorkHours(10);
        app.setWorkHoursForActivity(developer.getID(),activity.getName(),project.getID(),developer.getWorkHours());
        assertEquals(10,app.getProjectHM().get(project.getID()).getActivity(activity.getName()).getWorkedHours());
    }

}
