package acceptance_tests.steps;

import acceptance_tests.helper.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import System.App;

public class SystemSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private ActivityHelper activityHelper;

    public SystemSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, ActivityHelper activityHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.activityHelper = activityHelper;
    }

    // Joachim
    @Then("an error message {string} is given")
    public void anErrorWithMessageIsGiven(String string) {
        assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }

    // Christian
    @When("The project leader searches for available developers for the project and activity")
    public void theProjectLeaderSearchesForAvailableDevelopersForTheProjectAndActivity()  {
        try {
            app.searchAvailableDevelopers(projectHelper.getProject().getID(), activityHelper.getActivity().getName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }






















}