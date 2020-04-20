$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:use_cases/addDeveloperToSystem.feature");
formatter.feature({
  "name": "Add a developer to the system",
  "description": "  Description: A developer is added to the system\n  Actors: Developer",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "A developer is succesfully added to the system",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "There is a Developer with first name \"Ole\" and last name \"Smith\"",
  "keyword": "Given "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.thereIsADeveloperWithFirstNameAndLastName(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the developer with first name \"Ole\" and last name \"Smith\" is added to the system",
  "keyword": "When "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.theDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the developer with ID \"OLSM\" and first name \"Ole\" and last name \"Smith\" is in the system",
  "keyword": "Then "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.theDeveloperWithIDAndFirstNameAndLastNameIsInTheSystem(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("file:use_cases/createProject.feature");
formatter.feature({
  "name": "Create a project",
  "description": "  Description: A developer creates a project\n  Actors: Developer",
  "keyword": "Feature"
});
formatter.background({
  "name": "There is a developer registered in the system",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "There is a Developer with first name \"Ole\" and last name \"Smith\"",
  "keyword": "Given "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.thereIsADeveloperWithFirstNameAndLastName(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the developer with first name \"Ole\" and last name \"Smith\" is added to the system",
  "keyword": "When "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.theDeveloperWithFirstNameAndLastNameIsAddedToTheSystem(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the developer with ID \"OLSM\" and first name \"Ole\" and last name \"Smith\" is in the system",
  "keyword": "Then "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.theDeveloperWithIDAndFirstNameAndLastNameIsInTheSystem(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "A developer creates a project",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "A project with name \"Enigma Codebreaker \" is created",
  "keyword": "Given "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.aProjectWithNameIsCreated(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The project is added to the system",
  "keyword": "When "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.theProjectIsAddedToTheSystem()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "There is a project in the system with name \"Enigma Codebreaker\"",
  "keyword": "Then "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.thereIsAProjectInTheSystemWithName(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.uri("file:use_cases/initializeProject.feature");
formatter.feature({
  "name": "someone initializes a project",
  "description": "  Description: a project is initialized\n  Actors: Developer",
  "keyword": "Feature"
});
formatter.background({
  "name": "There is a project registered in the system",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "A project is created",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "The project is added to the system",
  "keyword": "When "
});
formatter.match({
  "location": "acceptance_tests.steps.SystemSteps.theProjectIsAddedToTheSystem()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "There is a project in the system",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "A project is initialized",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "The project has not been initialized",
  "keyword": "Given "
});
formatter.match({
  "location": "acceptance_tests.steps.ProjectSteps.theProjectHasNotBeenInitialized()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "The project is initialized by the user",
  "keyword": "When "
});
formatter.match({
  "location": "acceptance_tests.steps.ProjectSteps.theProjectIsInitializedByTheUser()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "The project is initialized",
  "keyword": "Then "
});
formatter.match({
  "location": "acceptance_tests.steps.ProjectSteps.theProjectIsInitialized()"
});
formatter.result({
  "status": "skipped"
});
});