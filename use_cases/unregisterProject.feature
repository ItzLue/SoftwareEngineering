# Joachim
Feature: Remove a project
  Description: A developer or project leader removes a project
  Actors: Developer\project leader

  Background: There is a developer, a project that contains an activity in the system and the developer is
              the project leader
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project

  # first scenario
  Scenario: A developer removes an uninitialized registered project succesfully
    When the active developer removes the project from the system
    Then the project is not contained in the system
    And the project does not contain any activities

  # second scenario
  Scenario: A project leader removes his registered project succesfully
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    When the active developer removes the project from the system
    Then the project is not contained in the system
    And the project does not contain any activities

  #Fail scenario
  Scenario: a developer removes an initialized project unsuccesfully
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And There is a Developer with first name "Birte" and last name "Rasmussen"
    And the developer is added to the system
    When The developer is set as the active developer
    Given the project leader is not the active user
    When the active developer removes the project from the system
    Then an error message "Only the project leader has access to remove the project" is given
