# Joachim
Feature: Remove activities from an initialized project
  Description: A developer removes an activity from an initialized project
  Actors: Developer

   #background
  Background: There is an uninitialized project in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project
    And the active developer assigns the developer to the activity


    #Main scenario
  Scenario: Activity is succesfully removed from an initialized project
    Given the project leader is the active user
    When the activity with name "Coding" is removed from the project
    Then the activity with name "Coding" is not in the project

     # Fail scenario :
  Scenario: Activity is unsuccesfully removed from an initialized project
    Given There is a Developer with first name "Birte" and last name "Rasmussen"
    And the developer is added to the system
    When The developer is set as the active developer
    Given the project leader is not the active user
    When the activity with name "Coding" is removed from the project
    Then an error message "Only the project leader has access to remove activities from a project" is given