package acceptance_tests.steps;

import System.App;
import domain.Project;
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
        assertFalse(false);
    }

    @When("The project is initialized by the user")
    public void theProjectIsInitializedByTheUser() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The project is initialized")
    public void theProjectIsInitialized() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
