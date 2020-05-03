package acceptance_tests.steps;

import acceptance_tests.helper.DeveloperHelper;
import acceptance_tests.helper.ErrorMessageHolder;
import acceptance_tests.helper.ProjectHelper;
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

    public SystemSteps(App app, ErrorMessageHolder errorMessageHolder, DeveloperHelper developerHelper, ProjectHelper projectHelper, MockDateHolder dateHolder) {
       this.app = app;
       this.errorMessageHolder = errorMessageHolder;
       this.developerHelper = developerHelper;
       this.projectHelper = projectHelper;
       this.dateHolder = dateHolder;
    }

    @Given("There is a Developer with first name {string} and last name {string}")
    public void thereIsADeveloperWithFirstNameAndLastName(String firstName, String lastName) {
        developerHelper.setDeveloper(new Developer(firstName, lastName));
        assertTrue(developerHelper.getDeveloper().getFirstName() == firstName && developerHelper.getDeveloper().getLastName() == lastName);
    }

    @When("the developer with first name {string} and last name {string} is added to the system")
    public void theDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(String firstName, String lastName) throws Exception {
        app.registerDeveloper(developerHelper.getDeveloper());
    }

    @Then("the developer with first name {string} and last name {string} and appropriate ID is in the system")
    public void theDeveloperWithFirstNameAndLastNameAndAppropriateIDIsInTheSystem(String string, String string2) {
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

    @Then("There is a project in the system with name {string}")
    public void thereIsAProjectInTheSystemWithName(String string) throws Exception{
        assertTrue(!(app.getProjectHM().isEmpty()));
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

    @When("The developer with ID {string} is set as the active developer")
    public void theDeveloperWithIDIsSetAsTheActiveDeveloper(String ID) {
        app.setActiveDeveloper(ID);
    }

    @Then("the developer with ID {string} is the active developer")
    public void theDeveloperWithIDIsTheActiveDeveloper(String ID) {
        assertEquals(app.getActiveDeveloper(),app.getDeveloperHM().get(ID));
    }


//    @When("the developer with ID {string} and name {string} is set as project leader for project with ID {string}")
//    public void theDeveloperWithIDAndNameIsSetAsProjectLeaderForProjectWithID(String arg0, String arg1, String arg2) {
//    }
//
//    @Then("the project with ID {string} has the developer with ID {string} and name {string} as project leader")
//    public void theProjectWithIDHasTheDeveloperWithIDAndNameAsProjectLeader(String arg0, String arg1, String arg2) {
//    }
//
//    @Then("The error message {string} is given")
//    public void theErrorMessageIsGiven(String arg0) {
//    }
}