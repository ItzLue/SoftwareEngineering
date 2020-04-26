package acceptance_tests.steps;

import System.App;
import domain.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Given("the following activities have been chosen for the project")
    public void theFollowingActivitiesHaveBeenChosenForTheProject() {

    }

    @Then("the developer with ID {string} and first name {string} and last name {string} is in the system")
    public void theDeveloperWithIDAndFirstNameAndLastNameIsInTheSystem(String arg0, String arg1, String arg2) {
    }

    @When("A new project with ID {string} and name {string}, start date {int}\\/{int}\\/{int}, end date {int}\\/{int}\\/{int} and the given activities is created")
    public void aNewProjectWithIDAndNameStartDateEndDateAndTheGivenActivitiesIsCreated(String arg0, String arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
    }

    @When("The project with ID {string} is added to the system")
    public void theProjectWithIDIsAddedToTheSystem(String arg0) {
    }

    @Then("There is a project with ID {string} in the system")
    public void thereIsAProjectWithIDInTheSystem(String arg0) {
    }

    @And("One or more of the activity start\\/end dates are not within the project start\\/end dates")
    public void oneOrMoreOfTheActivityStartEndDatesAreNotWithinTheProjectStartEndDates() {
    }

    @Then("The error message {string} is given")
    public void theErrorMessageIsGiven(String arg0) {
    }

    @When("A new project with ID {string} and and the given activities is created")
    public void aNewProjectWithIDAndAndTheGivenActivitiesIsCreated(String arg0) {
    }
}
