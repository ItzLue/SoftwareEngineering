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
    public void theStartDateOfTheActivityIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setActivityDate(true, projectHelper.getProject().getID(), activityHelper.getActivity().getName(), year, week);
        } catch (RuntimeException e) {
            exceptionHandler.add(e);
        }

    }

    @Then("The activity has the starting year {int} and the starting week {int}")
    public void theActivityHasTheStartingYearAndTheStartingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    @When("The end date of the activity is set to year {int} and week {int}")
    public void theEndDateOfTheActivityIsSetToYearAndWeek(int year, int week) throws IllegalAccessException {
        try {
            app.setActivityDate(false, projectHelper.getProject().getID(), activityHelper.getActivity().getName(), year, week);
        } catch (RuntimeException e) {
            exceptionHandler.add(e);
        }

    }

    @Then("The activity has the ending year {int} and the ending week {int}")
    public void theActivityHasTheEndingYearAndTheEndingWeek(int year, int week) {
        assertEquals(year, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.YEAR));
        assertEquals(week, activityHelper.getActivity().getInterval().getEndDate().get(Calendar.WEEK_OF_YEAR));
    }

    @When("the active developer assigns the developer to the activity")
    public void theDeveloperAssignsTheDeveloperToTheActivity() throws IllegalAccessException{
        try {
            app.setDeveloperToActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(),
                    developerHelper.getDeveloper().getID());
        } catch (Exception e) {

        }

    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



//    @When("the project leader assigns the developer to the activity")
//    public void theProjectLeaderAssignsTheDeveloperToTheActivity() throws Exception{
//        System.out.println("project leader: " + app.getActiveDeveloper());
//        app.setDeveloperToActivity(activityHelper.getActivity().getName(),projectHelper.getProject().getID(),
//                developerHelper.getDeveloper().getID());
//
//    }

    @Then("the activity has the developer assigned")
    public void theActivityHasTheDeveloperAssigned() {
        assertTrue(activityHelper.getActivity().developerHM.containsValue(developerHelper.getDeveloper()));
    }

    @Then("the developer has the activty on his or hers activity list")
    public void theDeveloperHasTheActivtyOnHisHersActivityList() {
        assertTrue(developerHelper.getDeveloper().getActivityList().contains(activityHelper.getActivity()));
    }

    @When("the activity with name {string} is removed from the project")
    public void theActivityWithNameIsRemovedFromTheProject(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }






}
