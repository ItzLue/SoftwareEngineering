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
    private MockDateHolder dateHolder;
    private ActivityHelper activityHelper;
    private ExceptionHandler exceptionHandler;

    public SystemSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder, ActivityHelper activityHelper, ExceptionHandler exceptionHandler) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
        this.activityHelper = activityHelper;
        this.exceptionHandler = exceptionHandler;
    }

    @Then("an error message {string} is given")
    public void anErrorWithMessageIsGiven(String string) {
        assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }



    /*
    @Then("An exception is given")
    public void anExceptionIsGiven() {
        assertFalse(exceptionHandler.getExceptions().isEmpty());
    }

    @Then("An exception is expected")
    public void anExceptionIsExpected() {
        exceptionHandler.expectException();
    }
     */


    @When("The project leader searches for available developers for the project and activity")
    public void theProjectLeaderSearchesForAvailableDevelopersForTheProjectAndActivity() throws IllegalAccessException {
        try {
            app.searchAvailableDevelopers(projectHelper.getProject().getID(), activityHelper.getActivity().getName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }






















}