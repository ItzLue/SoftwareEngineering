package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.*;
import domain.Activity;
import domain.Developer;
import domain.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class ActivitySteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private MockDateHolder dateHolder;
    private ActivityHelper activityHelper;
    private ExceptionHandler exceptionHandler;

    public ActivitySteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder, ActivityHelper activityHelper, ExceptionHandler exceptionHandler) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
        this.activityHelper = activityHelper;
        this.exceptionHandler = exceptionHandler;
    }

    @When("The start date of the activity is set to year {int} and week {int}")
    public void theStartDateOfTheActivityIsSetToYearAndWeek(int year, int week) {
        activityHelper.getActivity().getInterval().setStartDate(year,week);
    }

    @Then("The activity has the starting year {int} and the starting week {int}")
    public void theActivityHasTheStartingYearAndTheStartingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    @When("The end date of the activity is set to year {int} and week {int}")
    public void theEndDateOfTheActivityIsSetToYearAndWeek(int year, int week) {
        activityHelper.getActivity().getInterval().setEndDate(year,week);
    }

    @Then("The activity has the ending year {int} and the ending week {int}")
    public void theActivityHasTheEndingYearAndTheEndingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

}
