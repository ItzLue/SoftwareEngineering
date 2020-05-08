package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.*;
import domain.Activity;
import domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ProjectSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private MockDateHolder dateHolder;
    private ActivityHelper activityHelper;
    private ExceptionHandler exceptionHandler;

    public ProjectSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder, ActivityHelper activityHelper, ExceptionHandler exceptionHandler) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
        this.activityHelper = activityHelper;
        this.exceptionHandler = exceptionHandler;
    }


    @Given("A project with name {string} is created")
    public void aProjectWithNameIsCreated(String name) throws Exception{
        projectHelper.setProject(new Project(name));
    }

    @When("The project is added to the system")
    public void theProjectIsAddedToTheSystem() throws Exception{
        app.registerProject(projectHelper.getProject());
    }

    @Then("the project is registered in the system")
    public void theProjectIsRegisteredInTheSystem() {
        assertTrue(app.getProjectHM().containsValue(projectHelper.getProject()));
    }

    @Then("There is a project registered in the system with name {string}")
    public void thereIsAProjectRegisteredInTheSystemWithName(String string) {
        assertTrue(projectHelper.getProject().getName().equals(string));
        assertTrue(app.getProjectHM().containsValue(projectHelper.getProject()));
    }

    @Then("The project ID fits the current date")
    public void theProjectIDFitsTheCurrentDate() {
        String weekNumber = Integer.toString(app.getDate().get(Calendar.WEEK_OF_YEAR));
        String year = Integer.toString(app.getDate().get(Calendar.YEAR)).substring(2);
        String runningNumber = "0" + Integer.toString(app.getProjectHM().size());
        String test = year + weekNumber + runningNumber;

        assertTrue(test.equals(projectHelper.getProject().getID()));
    }

    @When("the active developer removes the project from the system")
    public void theActiveDeveloperRemovesTheProjectFromTheSystem() throws IllegalAccessException{
        try {
            app.removeProject(projectHelper.getProject().getID());
        }catch (IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project is not contained in the system")
    public void theProjectIsNotContainedInTheSystem() {
        assertFalse(app.getProjectHM().containsValue(projectHelper.getProject()));
    }

    @Given("The project has not been initialized")
    public void theProjectHasNotBeenInitialized() {
        assertFalse(projectHelper.getProject().isInitialized());
    }

    @Given("the developer is set as project leader for project with name {string}")
    public void theDeveloperIsSetAsProjectLeaderForProjectWithName(String string) {
        assertEquals(projectHelper.getProject().getName(), string);
        app.setProjectLeader(projectHelper.getProject().getID(), developerHelper.getDeveloper().getID());
    }

    @Then("the project with name {string} has the developer with first name {string} and last name {string} as project leader")
    public void theProjectWithNameHasTheDeveloperWithFirstNameAndLastNameAsProjectLeader(String projectName, String firstName, String lastName) {
        assertEquals(projectHelper.getProject().getProjectLeader().getFirstName(),firstName);
        assertEquals(projectHelper.getProject().getProjectLeader().getLastName(),lastName);
        assertEquals(projectHelper.getProject().getProjectLeader(),developerHelper.getDeveloper());
    }

    @Then("The project is initialized")
    public void theProjectIsInitialized() {
        assertTrue(projectHelper.getProject().isInitialized());
    }

    @Given("the project leader is the active user")
    public void theProjectLeaderIsTheActiveUser() {
        assertEquals(app.getActiveDeveloper(),developerHelper.getDeveloper());
    }
    @Given("the project leader is not the active user")
    public void theProjectLeaderIsNotTheActiveUser() {
        assertNotEquals(projectHelper.getProject().getProjectLeader(),app.getActiveDeveloper());
    }

    @Then("the project with name {string} has the developer as project leader")
    public void theProjectWithNameHasTheDeveloperAsProjectLeader(String string) {
        assertEquals(projectHelper.getProject().getName(), string);
        assertEquals(projectHelper.getProject().getProjectLeader(), developerHelper.getDeveloper());
    }

    @When("the activity with name {string} is added to the project")
    public void theActivityWithNameIsAddedToTheProject(String name) throws IllegalAccessException {
        try {
            app.registerActivityToProject(activityHelper.getActivity(), projectHelper.getProject().getID());
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with name {string} is in the project")
    public void theActivityWithNameIsInTheProject(String name) {
        boolean nameExists = false;
        for (Activity a : projectHelper.getProject().getActivityList()) {
            System.out.println("Activity name: " + a.getName());
            if (a.getName().equals(name)) {
                nameExists = true;
            }
        }
        assertTrue(nameExists);
    }

    @When("the activity with name {string} is removed from the project")
    public void theActivityWithNameIsRemovedFromTheProject(String string) throws IllegalAccessException {
        try {
            app.removeActivityFromProject(activityHelper.getActivity().getName(),projectHelper.getProject().getID());
        }catch (IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with name {string} is not in the project")
    public void theActivityWithNameIsNotInTheProject(String name) {
        boolean nameExists = false;
        for (Activity a : projectHelper.getProject().getActivityList()) {
            System.out.println("Activity name: " + a.getName());
            if (a.getName().equals(name)) {
                nameExists = true;
            }
        }
        assertFalse(nameExists);
    }

    @Then("the project does not contain any activities")
    public void theProjectDoesNotContainAnyActivities() {
        assertTrue(projectHelper.getProject().getActivityList().isEmpty());
    }

    @When("The name of the project is changed to {string}")
    public void theNameOfTheProjectIsChangedTo(String string) {
        projectHelper.getProject().setName(string);
    }

    @When("The start date of a project that doesnt exist is set")
    public void theStartDateOfAProjectThatDoesntExistIsSet() throws IllegalAccessException {
        try {
            app.setProjectDate(true,"badID",2020,35);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project is printed")
    public void theProjectIsPrinted() {
        projectHelper.getProject().toString();
    }
}
