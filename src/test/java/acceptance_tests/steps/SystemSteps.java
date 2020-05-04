package acceptance_tests.steps;

import acceptance_tests.helper.*;
import domain.Developer;
import domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import System.App;

import java.util.Calendar;
import java.util.List;

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

    @Given("There is a Developer with first name {string} and last name {string}")
    public void thereIsADeveloperWithFirstNameAndLastName(String firstName, String lastName) {
        developerHelper.setDeveloper(new Developer(firstName, lastName));
        assertTrue(developerHelper.getDeveloper().getFirstName() == firstName && developerHelper.getDeveloper().getLastName() == lastName);
    }

    @When("the developer is added to the system")
    public void theDeveloperIsAddedToTheSystem() throws Exception {
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


    @Given("The following developers are registered in the system")
    public void theFollowingDeveloperSIsAreRegisteredInTheSystem(List<List<String>> developers) {
        for (List<String> developerInfo : developers) {
            app.registerDeveloper(new Developer(developerInfo.get(0), developerInfo.get(1)));
        }
    }

    @Given("A project with name {string} is created")
    public void aProjectWithNameIsCreated(String name) throws Exception{
        projectHelper.setProject(new Project(name));
    }
    @When("The project is added to the system")
    public void theProjectIsAddedToTheSystem() throws Exception{
        app.registerProject(projectHelper.getProject());
    }

    @Then("the project is registered in the system")
    public void theProjectIsRegisteredInTheSystem() {
        assertTrue(app.getProjectHM().containsValue(projectHelper.getProject()));
    }
    @Then("There is a project registered in the system with name {string}")
    public void thereIsAProjectRegisteredInTheSystemWithName(String string) {
        assertTrue(projectHelper.getProject().getName().equals(string));
        assertTrue(app.getProjectHM().containsValue(projectHelper.getProject()));
    }


    @Then("The project with name {string} is uninitialized in the system")
    public void theProjectWithNameIsUninitializedInTheSystem(String string) {
        assertFalse(projectHelper.getProject().isInitialized());
    }

    @Then("The project ID fits the current date")
    public void theProjectIDFitsTheCurrentDate() {
        String weekNumber = Integer.toString(app.getDate().get(Calendar.WEEK_OF_YEAR));
        String year = Integer.toString(app.getDate().get(Calendar.YEAR)).substring(2);
        String runningNumber = Integer.toString(app.getProjectHM().size());
        String test = year + weekNumber + runningNumber;

        assertTrue(test.equals(projectHelper.getProject().getID()));
    }

    @When("a developer isn't selected as an active developer")
    public void aDeveloperIsnTSelectedAsAnActiveDeveloper() {
    }

    @Then("the active developer variable has the value null")
    public void theActiveDeveloperVariableHasTheValueNull() {
        assertTrue(app.getActiveDeveloper() == null);
    }

    @When("The developer is set as the active developer")
    public void theDeveloperIsSetAsTheActiveDeveloper() throws Exception{
        app.setActiveDeveloper(developerHelper.getDeveloper());
    }


    @Then("the developer is the active developer")
    public void theDeveloperIsTheActiveDeveloper() {
        assertEquals(app.getActiveDeveloper(),developerHelper.getDeveloper());

    }


}