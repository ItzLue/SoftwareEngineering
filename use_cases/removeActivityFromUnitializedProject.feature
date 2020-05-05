Feature: Remove activities from project
  Description: A developer removes an activity from a project
  Actors: Developer

  #background
  Background: There is an uninitialized project in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    And The project has not been initialized
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project

    #first scenario
  Scenario: Activity is removed from an uninitialized project
    Given there is an activity with name "Coding"
    When the activity with name "Coding" is added to the project
    Then the activity with name "Coding" is in the project
