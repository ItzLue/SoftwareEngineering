package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.*;
import domain.Activity;
import domain.Developer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

    @Given("The project has not been initialized")
    public void theProjectHasNotBeenInitialized() {
        assertFalse(projectHelper.getProject().isInitialized());
    }

    @Then("The project is initialized")
    public void theProjectIsInitialized() {
        assertTrue(projectHelper.getProject().isInitialized());
    }

    @When("the activity with name {string} is added to the project")
    public void theActivityWithNameIsAddedToTheProject(String name) throws IllegalAccessException {
        try {
            app.registerActivityToProject(activityHelper.getActivity(), projectHelper.getProject().getID());
        } catch(IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("an error message {string} is given")
    public void anErrorWithMessageIsGiven(String string) {
        assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }

    @When("the activity with name {string} is removed from the project")
    public void theActivityWithNameIsRemovedFromTheProject(String string) {
        System.out.println(developerHelper.getDeveloper().getActivityList().size());

        app.removeActivityFromProject(activityHelper.getActivity().getName(),projectHelper.getProject().getID());
        System.out.println(developerHelper.getDeveloper().getActivityList().size());
        System.out.println("tester");
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


    @Given("the developer is set as project leader for project with name {string}")
    public void theDeveloperIsSetAsProjectLeaderForProjectWithName(String string) {
        assertEquals(projectHelper.getProject().getName(), string);
        app.setProjectLeader(projectHelper.getProject().getID(), developerHelper.getDeveloper().getID());
        System.out.println("project leader: " + app.getActiveDeveloper());

    }

    @Then("the project with name {string} has the developer with first name {string} and last name {string} as project leader")
    public void theProjectWithNameHasTheDeveloperWithFirstNameAndLastNameAsProjectLeader(String string, String string2, String string3) {
        assertEquals(projectHelper.getProject().getProjectLeader().getFirstName(),string2);
        assertEquals(projectHelper.getProject().getProjectLeader().getLastName(),string3);
        assertEquals(projectHelper.getProject().getProjectLeader(),developerHelper.getDeveloper());

    }

    @When("the developer with first name {string} and last name {string} is set as project leader for project with name {string}")
    public void theDeveloperWithFirstNameAndLastNameIsSetAsProjectLeaderForProjectWithName(String string, String string2, String string3) throws Exception {
        assertEquals(developerHelper.getDeveloper().getFirstName(), string);
        assertEquals(developerHelper.getDeveloper().getLastName(), string2);
        assertEquals(projectHelper.getProject().getName(), string3);
        app.setProjectLeader(projectHelper.getProject().getID(), developerHelper.getDeveloper().getID());
    }

    @Then("the project with name {string} has the developer as project leader")
    public void theProjectWithNameHasTheDeveloperAsProjectLeader(String string) {
        assertEquals(projectHelper.getProject().getName(), string);
        assertEquals(projectHelper.getProject().getProjectLeader(), developerHelper.getDeveloper());

    }


    @Given("a developer with first name {string} and last name {string} is added to the system")
    public void aDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(String string, String string2) {
        developerHelper.setDeveloper(new Developer(string, string2));
        app.registerDeveloper(developerHelper.getDeveloper());
    }


    @When("The name of the project is changed to {string}")
    public void theNameOfTheProjectIsChangedTo(String string) {
        projectHelper.getProject().setName(string);
    }

    @When("The name of the activity with name {string} is changed to {string}")
    public void theNameOfTheActivityWithNameIsChangedTo(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there is an activity with name {string}")
    public void thereIsAnActivityWithName(String string) {
        activityHelper.setActivity(new Activity(string));
    }

    @Given("the project leader is the active user")
    public void theProjectLeaderIsTheActiveUser() {
        assertEquals(app.getActiveDeveloper(),developerHelper.getDeveloper());
    }
    @Given("the project leader is not the active user")
    public void theProjectLeaderIsNotTheActiveUser() {
        assertNotEquals(projectHelper.getProject().getProjectLeader(),app.getActiveDeveloper());
    }


    @Then("The error message {string} is given")
    public void theErrorMessageIsGiven(String string) {

    }

    @Then("An exception is expected")
    public void anExceptionIsExpected() {
        exceptionHandler.expectException();
    }

    @Then("An exception is given")
    public void anExceptionIsGiven() {
        assertFalse(exceptionHandler.getExceptions().isEmpty());
    }

    @When("the project is printed")
    public void theProjectIsPrinted() {
        projectHelper.getProject().toString();
    }

}
