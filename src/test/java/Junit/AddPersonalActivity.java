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

        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            app.setPersonalActivityDate(false, personalActivity.getName(), 2020, 19);
        });
        String expectedMessage = "Only the project leader has access to remove the project";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }

}
