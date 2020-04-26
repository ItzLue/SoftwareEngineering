package acceptance_tests.steps;

import System.App;
import domain.Activity;
import domain.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ProjectSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private MockDateHolder dateHolder;

    public ProjectSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
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


    @Then("the developer with ID {string} and first name {string} and last name {string} is in the system")
    public void theDeveloperWithIDAndFirstNameAndLastNameIsInTheSystem(String arg0, String arg1, String arg2) {
    }


    @When("The project with ID {string} is added to the system")
    public void theProjectWithIDIsAddedToTheSystem(String arg0) {
    }

    @Then("There is a project with ID {string} in the system")
    public void thereIsAProjectWithIDInTheSystem(String arg0) {
    }

    @Then("The error message {string} is given")
    public void theErrorMessageIsGiven(String arg0) {
    }

    @Given("the following activity have been chosen for the project")
    public void theFollowingActivityHaveBeenChosenForTheProject(List<List<String>> activity) {
        for (List<String> ActivityInfo: activity){
            app.registerActivityToProject(new Activity());
        }
    }

    @When("the activity are added to the project")
    public void theActivityAreAddedToTheProject() {
    }

    @Then("the activity are in the project")
    public void theActivityAreInTheProject() {
    }
}
