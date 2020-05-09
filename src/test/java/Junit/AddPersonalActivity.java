package Junit;

import domain.Activity;
import domain.Developer;
import domain.PersonalActivity;
import domain.Project;
import org.junit.jupiter.api.DisplayName;
import System.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Add personal activity")
public class AddPersonalActivity {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Codebreaker");
    private final Activity activity = new Activity("Coding");
    private final PersonalActivity personalActivity = new PersonalActivity("Skiing");

    @Test
    @DisplayName("Test Failing Asserts")
    public void testFailingAsserts() throws IllegalAccessException {
        app.registerDeveloper(developer);
        app.setActiveDeveloper(developer.getID());
        app.addPersonalActivity(personalActivity, developer.getID());
        System.out.println(app.activeDeveloper.getActivityList().size());
    }

    // Not active developer
    @Test
    @DisplayName("Test case A")
    public void addPersonalActivityDataSetA() throws IllegalAccessException {
        app.registerDeveloper(developer);
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            app.addPersonalActivity(personalActivity, developer.getID());
        });
        String expectedMessage = "You have to be an active developer to add personal activities";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    // Not a valid activity name
    @Test
    @DisplayName("Test case B")
    public void addPersonalActivityDataSetB() throws IllegalArgumentException {
        app.registerDeveloper(developer);
        app.setActiveDeveloper(developer.getID());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.addPersonalActivity(new PersonalActivity("2"), developer.getID());
        });
        String expectedMessage = "Activity names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Set valid activity date
    @Test
    @DisplayName("Test case C1")
    public void addPersonalActivityDataSetC1() throws IllegalArgumentException, IllegalAccessException {

        assertAll("personal activity",
                () -> {
                    app.registerDeveloper(developer);
                    app.setActiveDeveloper(developer.getID());
                    app.addPersonalActivity(personalActivity, developer.getID());
                    app.setPersonalActivityDate(true, personalActivity.getName(), 2020, 20);
                    app.setPersonalActivityDate(false, personalActivity.getName(), 2020, 23);
                    assertAll("check dates",
                            () -> assertEquals(2020, personalActivity.getInterval().getStartYear()),
                            () -> assertEquals(20, personalActivity.getInterval().getStartWeek()),
                            () -> assertEquals(2020, personalActivity.getInterval().getEndYear()),
                            () -> assertEquals(23, personalActivity.getInterval().getEndWeek())
                    );
                }
        );
    }

    // Set valid activity date
    @Test
    @DisplayName("Test case C2")
    public void addPersonalActivityDataSetC2() throws IllegalArgumentException, IllegalAccessException {
        app.registerDeveloper(developer);
        app.setActiveDeveloper(developer.getID());
        app.addPersonalActivity(personalActivity, developer.getID());
        app.setPersonalActivityDate(true, personalActivity.getName(), 2020, 20);
        app.setPersonalActivityDate(false, personalActivity.getName(), 2020, 22);
        assertNotNull(personalActivity.getInterval().getStartDate());
        assertNotNull(personalActivity.getInterval().getEndDate());
    }

    // Personal activity and project activity does not overlap
    @Test
    @DisplayName("Test case D1")
    public void addPersonalActivityDataSetD1() throws IllegalArgumentException, IllegalAccessException {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setActiveDeveloper(developer.getID());
        app.registerActivityToProject(activity,project.getID());
        activity.getInterval().setStartDate(2020,30);
        activity.getInterval().setEndDate(2020,33);
        app.addPersonalActivity(personalActivity, developer.getID());
        app.setPersonalActivityDate(true, personalActivity.getName(), 2020, 20);
        app.setPersonalActivityDate(false,personalActivity.getName(),2020,23);
        assertTrue(developer.isAvailable(activity.getInterval()));
    }

    // Personal activity and project activity does overlap
    @Test
    @DisplayName("Test case D2")
    public void addPersonalActivityDataSetD2() throws IllegalArgumentException, IllegalAccessException {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setActiveDeveloper(developer.getID());
        app.registerActivityToProject(activity,project.getID());
        activity.getInterval().setStartDate(2020,20);
        activity.getInterval().setEndDate(2020,25);
        app.addPersonalActivity(personalActivity, developer.getID());
        app.setPersonalActivityDate(true, personalActivity.getName(), 2020, 20);
        app.setPersonalActivityDate(false,personalActivity.getName(),2020,23);
        assertFalse(developer.isAvailable(activity.getInterval()));
    }

    // Personal activity and project activity does overlap
    @Test
    @DisplayName("Test case D3")
    public void addPersonalActivityDataSetD3() throws IllegalArgumentException, IllegalAccessException {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setActiveDeveloper(developer.getID());
        app.registerActivityToProject(activity,project.getID());
        activity.getInterval().setStartDate(2020,20);
        activity.getInterval().setEndDate(2020,25);
        app.addPersonalActivity(personalActivity, developer.getID());
        app.setPersonalActivityDate(true, personalActivity.getName(), 2020, 20);
        app.setPersonalActivityDate(false,personalActivity.getName(),2020,23);
        assertFalse(developer.isAvailable(activity.getInterval()));
    }

    // Different years activity 1 year later than personal activity
    @Test
    @DisplayName("Test case D4")
    public void addPersonalActivityDataSetD4() throws IllegalArgumentException, IllegalAccessException {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setActiveDeveloper(developer.getID());
        app.registerActivityToProject(activity,project.getID());
        activity.getInterval().setStartDate(2021,20);
        activity.getInterval().setEndDate(2021,25);
        app.addPersonalActivity(personalActivity, developer.getID());
        app.setPersonalActivityDate(true, personalActivity.getName(), 2020, 20);
        app.setPersonalActivityDate(false,personalActivity.getName(),2020,23);
        assertTrue(developer.isAvailable(activity.getInterval()));
    }

    // Different years
    @Test
    @DisplayName("Test case D5")
    public void addPersonalActivityDataSetD5() throws IllegalArgumentException, IllegalAccessException {
        app.registerDeveloper(developer);
        app.registerProject(project);
        app.setActiveDeveloper(developer.getID());
        app.registerActivityToProject(activity,project.getID());
        activity.getInterval().setStartDate(2020,20);
        activity.getInterval().setEndDate(2020,25);
        app.addPersonalActivity(personalActivity, developer.getID());
        app.setPersonalActivityDate(true, personalActivity.getName(), 2021, 20);
        app.setPersonalActivityDate(false,personalActivity.getName(),2021,23);
        assertTrue(developer.isAvailable(activity.getInterval()));
    }

    // No personal activities
    @Test
    @DisplayName("Test case E")
    public void addPersonalActivityDataSetE() throws IllegalArgumentException, IllegalAccessException {
       app.registerDeveloper(developer);
       assertNull(developer.getPersonalActivity(personalActivity.getName()));
    }

}
