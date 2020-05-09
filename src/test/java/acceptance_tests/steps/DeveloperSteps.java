package acceptance_tests.steps;

import acceptance_tests.helper.*;
import domain.Developer;
import domain.PersonalActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import System.App;

public class DeveloperSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private MockDateHolder dateHolder;
    private ActivityHelper activityHelper;
    private PersonalActivityHelper personalActivityHelper;
    private ExceptionHandler exceptionHandler;

    public DeveloperSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder, ActivityHelper activityHelper, ExceptionHandler exceptionHandler,PersonalActivityHelper personalActivityHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.dateHolder = dateHolder;
        this.activityHelper = activityHelper;
        this.personalActivityHelper = personalActivityHelper;
        this.exceptionHandler = exceptionHandler;
    }

    @Given("There is a Developer with first name {string} and last name {string}")
    public void thereIsADeveloperWithFirstNameAndLastName(String firstName, String lastName) {
        developerHelper.setDeveloper(new Developer(firstName, lastName));
        assertTrue(developerHelper.getDeveloper().getFirstName() == firstName &&
                developerHelper.getDeveloper().getLastName() == lastName);
    }

    @When("the developer is added to the system")
    public void theDeveloperIsAddedToTheSystem() throws Exception {
        app.registerDeveloper(developerHelper.getDeveloper());
    }

    @Given("a developer with first name {string} and last name {string} is added to the system")
    public void aDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(String string, String string2) {
        developerHelper.setDeveloper(new Developer(string, string2));
        app.registerDeveloper(developerHelper.getDeveloper());
    }

    @Then("the developer is in the system with an appropriate ID")
    public void theDeveloperIsInTheSystemWithAnAppropriateID() {
        String testID;
        if (app.getDeveloperHM().size() > 9) {
            testID = developerHelper.getDeveloper().getFirstName().substring(0,2).toUpperCase() + developerHelper.getDeveloper().getLastName().substring(0,2).toUpperCase() + (app.developerHM.size());
        } else {
            testID = developerHelper.getDeveloper().getFirstName().substring(0,2).toUpperCase() + developerHelper.getDeveloper().getLastName().substring(0,2).toUpperCase() + 0 + (app.developerHM.size());
        }
        assertTrue(testID.equals(developerHelper.getDeveloper().getID()));
        assertTrue(app.getDeveloperHM().containsKey(developerHelper.getDeveloper().getID()));
    }

    @When("the developer is being removed from the system")
    public void theDeveloperIsBeingRemovedFromTheSystem() {
        app.removeDeveloper(developerHelper.getDeveloper().getID());
    }

    @Then("the developer is not contained in the system")
    public void theDeveloperIsNotContainedInTheSystem() {
        assertFalse(app.getDeveloperHM().containsValue(developerHelper.getDeveloper()));
    }

    @When("The developer is set as the active developer")
    public void theDeveloperIsSetAsTheActiveDeveloper() throws Exception{
        app.setActiveDeveloper(developerHelper.getDeveloper());
    }

    @Then("the developer is the active developer")
    public void theDeveloperIsTheActiveDeveloper() {
        assertEquals(app.getActiveDeveloper(),developerHelper.getDeveloper());
    }

    @When("the personal activity is added to the developer")
    public void thePersonalActivityIsAddedToTheDeveloper() {
        developerHelper.getDeveloper().addPersonalActivity(personalActivityHelper.getPersonalActivity());
    }

    @Then("the developer contains the personal activity")
    public void theDeveloperContainsThePersonalActivity() {
        assertTrue(app.getDeveloperHM().get(developerHelper.getDeveloper().getID()).getPersonalActivityList().contains(personalActivityHelper.getPersonalActivity()));
    }

    @When("the developer removes the personal activity")
    public void theDeveloperRemovesThePersonalActivity() {
        developerHelper.getDeveloper().removePersonalActivity(personalActivityHelper.getPersonalActivity());
    }

    @Then("the personal activity is not contained in the developer")
    public void thePersonalActivityIsNotContainedInTheDeveloper() {
        assertFalse(developerHelper.getDeveloper().getPersonalActivityList().contains(personalActivityHelper.getPersonalActivity()));
    }


    @Then("a developer hasn't been selected as an active developer")
    public void aDeveloperHasnTBeenSelectedAsAnActiveDeveloper() {
        assertEquals(app.getActiveDeveloper(),null);
    }

    @When("All developers are printed")
    public void allDevelopersArePrinted() {
        app.getDevValues();
    }
}
