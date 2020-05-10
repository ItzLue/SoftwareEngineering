package acceptance_tests.steps;

import System.App;
import acceptance_tests.helper.*;
import domain.Activity;
import domain.PersonalActivity;
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
    private ActivityHelper activityHelper;
    private PersonalActivityHelper personalActivityHelper;

    public ActivitySteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, ActivityHelper activityHelper, PersonalActivityHelper personalActivityHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.activityHelper = activityHelper;
        this.personalActivityHelper = personalActivityHelper;
    }

    // Joachim
    @Given("there is an activity with name {string}")
    public void thereIsAnActivityWithName(String string) {
        activityHelper.setActivity(new Activity(string));
    }

    // Christian
    @When("The start date of the activity is set to year {int} and week {int}")
    public void theStartDateOfTheActivityIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setActivityDate(true, projectHelper.getProject().getID(), activityHelper.getActivity().getName(), year, week);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Christian
    @When("The end date of the activity is set to year {int} and week {int}")
    public void theEndDateOfTheActivityIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setActivityDate(false, projectHelper.getProject().getID(), activityHelper.getActivity().getName(), year, week);
        } catch (RuntimeException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Christian
    @Then("The activity has the starting year {int} and the starting week {int}")
    public void theActivityHasTheStartingYearAndTheStartingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    // Christian
    @Then("The activity has the ending year {int} and the ending week {int}")
    public void theActivityHasTheEndingYearAndTheEndingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.WEEK_OF_YEAR));
    }

    // Christian
    @Given("The activity planned hours is set to {double}")
    public void theActivityPlannedHoursIsSetTo(Double hours) throws IllegalAccessException {
        try {
            app.setPlannedHoursForActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(),hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Regin
    @When("the active developer assigns the developer to the activity")
    public void theDeveloperAssignsTheDeveloperToTheActivity() throws IllegalAccessException{
        try {
            app.setDeveloperToActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(),
                    developerHelper.getDeveloper().getID());
        } catch (IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Christian
    @Then("the activity has the developer assigned")
    public void theActivityHasTheDeveloperAssigned() {
        assertTrue(activityHelper.getActivity().developerHM.containsValue(developerHelper.getDeveloper()));
    }

    // Christian
    @Then("the developer has the activty on his or hers activity list")
    public void theDeveloperHasTheActivtyOnHisHersActivityList() {
        assertTrue(developerHelper.getDeveloper().getActivityList().contains(activityHelper.getActivity()));
    }

    // Regin
    @Then("the activty does not contain the developer")
    public void theActivtyDoesNotContainTheDeveloper() {
        assertFalse(activityHelper.getActivity().developerHM.containsValue(developerHelper.getDeveloper()));
    }

    // Christian
    @When("The developer sets their worked hours for the activity to {double}")
    public void theDeveloperSetsTheirWorkedHoursForTheActivityTo(Double hours) throws IllegalArgumentException {
        try {
            app.setWorkedHoursForActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(), hours);
        } catch(IllegalArgumentException | IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Christian
    @Then("The developer and activity worked hours are {double}")
    public void theDeveloperAndActivityWorkedHoursAre(Double hours) {
        assertTrue(hours == developerHelper.getDeveloper().getWorkedHours());
        assertTrue(hours == activityHelper.getActivity().getWorkedHours());
    }

    // Loui
    @Given("there is a personal activity with name {string}")
    public void thereIsAPersonalActivityWithName(String name) {
        personalActivityHelper.setPersonalActivity(new PersonalActivity(name));
    }
}
