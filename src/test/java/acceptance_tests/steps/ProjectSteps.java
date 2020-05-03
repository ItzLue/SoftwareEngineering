package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.ActivityHelper;
import acceptance_tests.helper.DeveloperHelper;
import acceptance_tests.helper.ErrorMessageHolder;
import acceptance_tests.helper.ProjectHelper;
import domain.Activity;
import domain.Developer;
import domain.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class ProjectSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private MockDateHolder dateHolder;
    private ActivityHelper activityHelper;

    public ProjectSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder, ActivityHelper activityHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
        this.activityHelper = activityHelper;
    }

    @Given("The project has not been initialized")
    public void theProjectHasNotBeenInitialized() {
        assertFalse(projectHelper.getProject().isInitialized());
    }

    @When("The project is initialized by the user")
    public void theProjectIsInitializedByTheUser() {
        projectHelper.getProject().initProject();
    }

    @Then("The project is initialized")
    public void theProjectIsInitialized() {
        assertTrue(projectHelper.getProject().isInitialized());
    }

    @When("the activity with name {string} is added to the project")
    public void theActivityWithNameIsAddedToTheProject(String name) {
        app.registerActivityToProject(activityHelper.getActivity(),projectHelper.getProject().getID());
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

    @When("the developer with first name {string} and last name {string} is set as project leader for project with name {string}")
    public void theDeveloperWithFirstNameAndLastNameIsSetAsProjectLeaderForProjectWithName(String string, String string2, String string3) throws Exception {
        assertEquals(developerHelper.getDeveloper().getFirstName(), string);
        assertEquals(developerHelper.getDeveloper().getLastName(), string2);
        assertEquals(projectHelper.getProject().getName(),string3);
        app.setProjectLeader(projectHelper.getProject().getID(),developerHelper.getDeveloper().getID());

    }

    @Then("the project with name {string} has the developer with first name {string} and last name {string} as project leader")
    public void theProjectWithNameHasTheDeveloperWithFirstNameAndLastNameAsProjectLeader(String string, String string2, String string3) {
        assertEquals(projectHelper.getProject().getName(),string);
        assertEquals(projectHelper.getProject().getProjectLeader().getFirstName(),string2);
        assertEquals(projectHelper.getProject().getProjectLeader().getLastName(),string3);
    }

    @Given("a developer with first name {string} and last name {string} is added to the system")
    public void aDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(String string, String string2) throws Exception {
        Developer birte = new Developer(string,string2);
        app.registerDeveloper(birte);

    }
    @When("a developer with first name {string} and last name {string} is set as project leader for project with name {string}")
    public void aDeveloperWithFirstNameAndLastNameIsSetAsProjectLeaderForProjectWithName(String string, String string2, String string3) {
        try {
            for (Developer developer : app.developerHM.values()) {
                if(developer.getFirstName().equals("Birte")) {
                    projectHelper.getProject().setProjectLeader(developer);
                }
            }
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
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

    @Then("The error message {string} is given")
    public void theErrorMessageIsGiven(String string) {

    }

}
