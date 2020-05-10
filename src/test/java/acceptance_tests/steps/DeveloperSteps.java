package acceptance_tests.steps;

import acceptance_tests.helper.*;
import domain.Developer;
import domain.PersonalActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import System.App;

import static org.junit.Assert.*;

public class DeveloperSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private DeveloperHelper developerHelper;
    private ProjectHelper projectHelper;
    private ActivityHelper activityHelper;
    private PersonalActivityHelper personalActivityHelper;

    public DeveloperSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, ActivityHelper activityHelper, PersonalActivityHelper personalActivityHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.developerHelper = developerHelper;
        this.projectHelper = projectHelper;
        this.activityHelper = activityHelper;
        this.personalActivityHelper = personalActivityHelper;
    }

    // Joachim
    @Given("There is a Developer with first name {string} and last name {string}")
    public void thereIsADeveloperWithFirstNameAndLastName(String firstName, String lastName) {
        developerHelper.setDeveloper(new Developer(firstName, lastName));
        assertTrue(developerHelper.getDeveloper().getFirstName() == firstName &&
                developerHelper.getDeveloper().getLastName() == lastName);
    }

    // Joachim
    @When("the developer is added to the system")
    public void theDeveloperIsAddedToTheSystem() throws Exception {
        app.registerDeveloper(developerHelper.getDeveloper());
    }

    // Christian
    @Given("a developer with first name {string} and last name {string} is added to the system")
    public void aDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(String string, String string2) {
        developerHelper.setDeveloper(new Developer(string, string2));
        app.registerDeveloper(developerHelper.getDeveloper());
    }

    // Regin
    @Then("the developer is in the system with an appropriate ID")
    public void theDeveloperIsInTheSystemWithAnAppropriateID() {
        String testID;
        if (app.getDeveloperHM().size() > 9) {
            testID = developerHelper.getDeveloper().getFirstName().substring(0,2).toUpperCase() + developerHelper.getDeveloper().getLastName().substring(0,2).toUpperCase() + (app.developerHM.size());
        } else {
            testID = developerHelper.getDeveloper().getFirstName().substring(0,2).toUpperCase() + developerHelper.getDeveloper().getLastName().substring(0,2).toUpperCase() + 0 + (app.developerHM.size());
        }
        assertEquals(testID, developerHelper.getDeveloper().getID());
        assertTrue(app.getDeveloperHM().containsKey(developerHelper.getDeveloper().getID()));
    }

    // Regin
    @When("the developer is being removed from the system")
    public void theDeveloperIsBeingRemovedFromTheSystem() {
        app.removeDeveloper(developerHelper.getDeveloper().getID());
    }

    // Regin
    @Then("the developer is not contained in the system")
    public void theDeveloperIsNotContainedInTheSystem() {
        assertFalse(app.getDeveloperHM().containsValue(developerHelper.getDeveloper()));
    }

    // Joachim
    @When("The developer is set as the active developer")
    public void theDeveloperIsSetAsTheActiveDeveloper() throws Exception{
        app.setActiveDeveloper(developerHelper.getDeveloper());
    }

    // Regin
    @Then("the developer is the active developer")
    public void theDeveloperIsTheActiveDeveloper() {
        assertEquals(app.getActiveDeveloper(),developerHelper.getDeveloper());
    }

    // Loui
    @When("the personal activity is added to the developer")
    public void thePersonalActivityIsAddedToTheDeveloper() {
        developerHelper.getDeveloper().addPersonalActivity(personalActivityHelper.getPersonalActivity());
    }

    // Loui
    @Then("the developer contains the personal activity")
    public void theDeveloperContainsThePersonalActivity() {
        assertTrue(app.getDeveloperHM().get(developerHelper.getDeveloper().getID()).getPersonalActivityList().contains(personalActivityHelper.getPersonalActivity()));
    }

    // Loui
    @When("the developer removes the personal activity")
    public void theDeveloperRemovesThePersonalActivity() {
        developerHelper.getDeveloper().removePersonalActivity(personalActivityHelper.getPersonalActivity());
    }

    // Loui
    @Then("the personal activity is not contained in the developer")
    public void thePersonalActivityIsNotContainedInTheDeveloper() {
        assertFalse(developerHelper.getDeveloper().getPersonalActivityList().contains(personalActivityHelper.getPersonalActivity()));
    }


    // Joachim
    @Then("a developer hasn't been selected as an active developer")
    public void aDeveloperHasnTBeenSelectedAsAnActiveDeveloper() {
        assertNull(app.getActiveDeveloper());
    }

    // Regin
    @When("All developers are printed")
    public void allDevelopersArePrinted() {
        app.getDevValues();
    }
}
