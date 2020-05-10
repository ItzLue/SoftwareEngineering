package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

import static org.junit.Assert.*;

public class IntervalSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private ActivityHelper activityHelper;

    public IntervalSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, ActivityHelper activityHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.activityHelper = activityHelper;
    }

    // Christian
    @When("The start date of the project is set to year {int} and week {int}")
    public void theStartDateOfTheProjectIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setProjectDate(true, projectHelper.getProject().getID(), year, week);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Christian
    @Then("The project has the starting year {int} and the starting week {int}")
    public void theProjectHasTheStartingYearAndTheStartingWeek(int year, int week) {
        assertEquals(year, projectHelper.getProject().getInterval().getStartDate().get(Calendar.YEAR));
        assertEquals(week, projectHelper.getProject().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    // Christian
    @When("The end date of the project is set to year {int} and week {int}")
    public void theEndDateOfTheProjectIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setProjectDate(false, projectHelper.getProject().getID(), year, week);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Christian
    @Then("The project has the ending year {int} and the ending week {int}")
    public void theProjectHasTheEndingYearAndTheEndingWeek(int year, int week) {
        assertEquals(year, projectHelper.getProject().getInterval().getEndDate().get(Calendar.YEAR));
        assertEquals(week, projectHelper.getProject().getInterval().getEndDate().get(Calendar.WEEK_OF_YEAR));
    }

    // Christian
    @When("The start date of a nonexistent activity is set")
    public void theStartDateOfANonexistentActivityIsSet() {
        try {
            app.setActivityDate(true,projectHelper.getProject().getID(),"invalidName",2020,35);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
