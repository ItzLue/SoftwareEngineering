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
    private MockDateHolder dateHolder;
    private ActivityHelper activityHelper;
    private ExceptionHandler exceptionHandler;

    public IntervalSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder, ActivityHelper activityHelper, ExceptionHandler exceptionHandler) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
        this.activityHelper = activityHelper;
        this.exceptionHandler = exceptionHandler;
    }

    @When("The start date of the project is set to year {int} and week {int}")
    public void theStartDateOfTheProjectIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setProjectDate(true, projectHelper.getProject().getID(), year, week);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("The project has the starting year {int} and the starting week {int}")
    public void theProjectHasTheStartingYearAndTheStartingWeek(int year, int week) {
        assertEquals(year, projectHelper.getProject().getInterval().getStartDate().get(Calendar.YEAR));
        assertEquals(week, projectHelper.getProject().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    @When("The end date of the project is set to year {int} and week {int}")
    public void theEndDateOfTheProjectIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setProjectDate(false, projectHelper.getProject().getID(), year, week);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("The project has the ending year {int} and the ending week {int}")
    public void theProjectHasTheEndingYearAndTheEndingWeek(int year, int week) {
        assertEquals(year, projectHelper.getProject().getInterval().getEndDate().get(Calendar.YEAR));
        assertEquals(week, projectHelper.getProject().getInterval().getEndDate().get(Calendar.WEEK_OF_YEAR));
    }
}
