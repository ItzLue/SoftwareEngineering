package acceptance_tests.steps;

import acceptance_tests.helper.ActivityHelper;
import acceptance_tests.helper.DeveloperHelper;
import acceptance_tests.helper.ErrorMessageHolder;
import acceptance_tests.helper.ProjectHelper;
import domain.Activity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import System.App;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
        app.registerActivityToProject(name,projectHelper.getProject().getID());
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
    public void theDeveloperWithFirstNameAndLastNameIsSetAsProjectLeaderForProjectWithName(String arg0, String arg1, String arg2) {

    }

    @Then("the project with name {string} has the developer with first name {string} and last name {string} as project leader")
    public void theProjectWithNameHasTheDeveloperWithFirstNameAndLastNameAsProjectLeader(String arg0, String arg1, String arg2) {

    }
}
