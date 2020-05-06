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
    private final Developer developer = new Developer("Jane", "Doe");
    private final Project project = new Project("Enigma Codebreaker");


    // No input
    @Test
    @DisplayName("Test case A")
    public void registerProjectDataSetA() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerProject(new Project(""));
        });
        String expectedMessage = "Project names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test case B1")
    public void registerProjectDataSetB1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.registerProject(new Project("1"));
        });
        String expectedMessage = "Project names must be longer than one letter";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    @DisplayName("Test case B2")
    void registerProjectDataSetB2() {
        assertThrows(IllegalArgumentException.class, () -> {
            app.registerProject(new Project("!#¤!"));
        });
    }

    @Test
    @DisplayName("Test case C")
    void registerDeveloperDataSetC() {
        app.registerProject(project);
        assertEquals(0, app.getProjectHM().get(project.getID()).getActivityList().size());
    }

    @Test
    @DisplayName("Test case D")
    void registerDeveloperDataSetD() {
        app.registerProject(project);
        String projectID = project.getID();
        assertEquals("20191", projectID);
        assertEquals("Enigma Codebreaker", app.getProjectHM().get(projectID).getName());
    }

    // Change project name
    @Test
    @DisplayName("Test case E")
    void registerDeveloperDataSetE() {
        app.registerProject(project);
        assertAll("make project",
                () -> {
                    assertEquals("Enigma Codebreaker", app.getProjectHM().get("20191").getName());
                    app.setProjectName("20191", "Minecraft");
                    assertAll("Change name",
                            () -> assertEquals("Minecraft", app.getProjectHM().get("20191").getName()),
                            () -> assertEquals("20191", app.getProjectHM().get("20191").getID())
                    );
                }
        );
    }
}



    /*
    Project tests
     */

//    @Test
//    void registerProject() {
//        // Within a code block, if an assertion fails the
//        // subsequent code in the same block will be skipped.
//        app.registerDeveloper(developer);
//        app.registerProject(project);
//        String projectID = "20191";
//        String developerID = "JADO01";
//        assertAll("project",
//                () -> {
//            assertNotNull(app.getProjectHM().get(projectID));
//                    assertAll("project properties",
//                            () -> assertEquals("20191", app.getProjectHM().get(projectID).getID()),
//                            () -> assertEquals("Enigma Coding", app.getProjectHM().get(projectID).getName()),
//                            () -> assertNull(app.projectHM.get(projectID).getInterval().getStartDate()),
//                            () -> assertEquals(0, app.getProjectHM().get(projectID).getActivityList().size())
//                    );
//                },
//                () -> {
//            app.registerActivityToProject(new Activity("Coding"),projectID);
//            app.setActivityDate(true,projectID,"Coding",2020,26);
//            app.setActivityDate(false,projectID,"Coding",2020,29);
//            assertAll("Register activities",
//                    () -> assertEquals(1,app.getProjectHM().get(projectID).getActivityList().size()),
//                    () -> assertEquals("Coding",app.getProjectHM().get(projectID).getActivity("Coding").getName())
//                    );
//            assertAll("Activity start and end date",
//                    () -> assertEquals(26,app.getProjectHM().get(projectID).getActivity("Coding").getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR)),
//                    () -> assertEquals(2020,app.getProjectHM().get(projectID).getActivity("Coding").getInterval().getStartDate().get(Calendar.YEAR)),
//                    () -> assertEquals(29,app.getProjectHM().get(projectID).getActivity("Coding").getInterval().getEndDate().get(Calendar.WEEK_OF_YEAR)),
//                    () -> assertEquals(2020,app.getProjectHM().get(projectID).getActivity("Coding").getInterval().getEndDate().get(Calendar.YEAR))
//                    );
//                },
//                () -> {
//                    app.setProjectLeader(projectID,developerID);
//                    assertAll("Set project leader",
//                            ()->assertNotNull(app.getProjectHM().get(projectID).getProjectLeader().getID())
//                    );
//                }
//        );
//    }

