package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.*;
import domain.Activity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

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

    @Given("there is an activity with name {string}")
    public void thereIsAnActivityWithName(String string) {
        activityHelper.setActivity(new Activity(string));
    }

    @When("The start date of the activity is set to year {int} and week {int}")
    public void theStartDateOfTheActivityIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setActivityDate(true, projectHelper.getProject().getID(), activityHelper.getActivity().getName(), year, week);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("The end date of the activity is set to year {int} and week {int}")
    public void theEndDateOfTheActivityIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setActivityDate(false, projectHelper.getProject().getID(), activityHelper.getActivity().getName(), year, week);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("The activity has the starting year {int} and the starting week {int}")
    public void theActivityHasTheStartingYearAndTheStartingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    @Then("The activity has the ending year {int} and the ending week {int}")
    public void theActivityHasTheEndingYearAndTheEndingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.WEEK_OF_YEAR));
    }

    @Given("The activity planned hours is set to {double}")
    public void theActivityPlannedHoursIsSetTo(Double hours) throws IllegalAccessException {
        try {
            app.setPlannedHoursForActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(),hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the active developer assigns the developer to the activity")
    public void theDeveloperAssignsTheDeveloperToTheActivity() throws IllegalAccessException{
        try {
            app.setDeveloperToActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(),
                    developerHelper.getDeveloper().getID());
        } catch (IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity has the developer assigned")
    public void theActivityHasTheDeveloperAssigned() {
        assertTrue(activityHelper.getActivity().developerHM.containsValue(developerHelper.getDeveloper()));
    }

    @Then("the developer has the activty on his or hers activity list")
    public void theDeveloperHasTheActivtyOnHisHersActivityList() {
        assertTrue(developerHelper.getDeveloper().getActivityList().contains(activityHelper.getActivity()));
    }

    @Then("the activty does not contain the developer")
    public void theActivtyDoesNotContainTheDeveloper() {
        assertFalse(activityHelper.getActivity().developerHM.containsValue(developerHelper.getDeveloper()));
    }

    @When("The developer sets their worked hours for the activity to {double}")
    public void theDeveloperSetsTheirWorkedHoursForTheActivityTo(Double hours) throws IllegalArgumentException {
        try {
            app.setWorkedHoursForActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(), hours);
        } catch(IllegalArgumentException | IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("The developer and activity worked hours are {double}")
    public void theDeveloperAndActivityWorkedHoursAre(Double hours) {
        assertTrue(hours == developerHelper.getDeveloper().getWorkedHours());
        assertTrue(hours == activityHelper.getActivity().getWorkedHours());
    }
}
