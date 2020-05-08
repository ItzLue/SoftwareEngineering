package Junit;

import domain.Activity;
import domain.Developer;
import domain.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import System.App;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Remove project")
public class RemoveProject {
    private final App app = new App();
    private final Developer developer = new Developer("Jane", "Doe");
    private final Developer developer2 = new Developer("John", "Doe");
    private final Project project = new Project("Enigma Codebreaker");
    private final Activity activity = new Activity("Coding");

    @Test
    @DisplayName("Test failing asserts")
    void testFailingAsserts() throws IllegalAccessException {
        app.registerProject(project);
        app.removeProject(project.getID());

    }

    // Project initialized  - remove project by an developer that is not project leader
    @Test
    @DisplayName("Test case A")
    void RemoveProjectDataSetA() {
        app.registerDeveloper(developer);
        app.registerDeveloper(developer2);
        app.registerProject(project);
        app.setProjectLeader(project.getID(), developer2.getID());
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            app.removeProject(project.getID());
        });
        String expectedMessage = "Only the project leader has access to remove the project";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Add 12 projects - and remove 10 projects with activities
    @Test
    @DisplayName("Test case B")
    void RemoveProjectDataSetB() throws IllegalAccessException {

        assertAll("project",
                () -> {
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));
                    app.registerProject(new Project("Codebreaker"));

                    assertAll("ID",
                            () -> assertEquals("201901", app.getProjectHM().get("201901").getID()),
                            () -> assertEquals("201902", app.getProjectHM().get("201902").getID()),
                            () -> assertEquals("201903", app.getProjectHM().get("201903").getID()),
                            () -> assertEquals("201904", app.getProjectHM().get("201904").getID()),
                            () -> assertEquals("201905", app.getProjectHM().get("201905").getID()),
                            () -> assertEquals("201906", app.getProjectHM().get("201906").getID()),
                            () -> assertEquals("201907", app.getProjectHM().get("201907").getID()),
                            () -> assertEquals("201908", app.getProjectHM().get("201908").getID()),
                            () -> assertEquals("201909", app.getProjectHM().get("201909").getID()),
                            () -> assertEquals("201910", app.getProjectHM().get("201910").getID()),
                            () -> assertEquals("201911", app.getProjectHM().get("201911").getID()),
                            () -> assertEquals("201912", app.getProjectHM().get("201912").getID())
                    );
                    assertAll("activites",
                            () -> {
                                app.registerActivityToProject(activity, "201901");
                                app.registerActivityToProject(activity, "201902");
                                app.registerActivityToProject(activity, "201903");
                                app.registerActivityToProject(activity, "201904");
                                app.registerActivityToProject(activity, "201905");
                                app.registerActivityToProject(activity, "201906");
                                app.registerActivityToProject(activity, "201907");
                                app.registerActivityToProject(activity, "201908");
                                app.registerActivityToProject(activity, "201909");
                                app.registerActivityToProject(activity, "201910");
                                app.registerActivityToProject(activity, "201911");
                                app.registerActivityToProject(activity, "201912");
                            }
                    );
                },
                () -> {
                    app.removeProject("201901");
                    app.removeProject("201902");
                    app.removeProject("201903");
                    app.removeProject("201904");
                    app.removeProject("201905");
                    app.removeProject("201906");
                    app.removeProject("201907");
                    app.removeProject("201908");
                    app.removeProject("201909");
                    app.removeProject("201910");

                    assertAll("removed",
                            () -> assertFalse(app.getProjectHM().containsKey("2019101")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019102")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019103")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019104")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019105")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019106")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019107")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019108")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019109")),
                            () -> assertFalse(app.getProjectHM().containsKey("2019110")),
                            () -> assertTrue(app.getProjectHM().containsKey("201911")),
                            () -> assertTrue(app.getProjectHM().containsKey("201912"))
                    );
                }
        );
    }
}

