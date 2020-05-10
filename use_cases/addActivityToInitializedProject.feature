# Joachim
Feature: Add activities to an initialized project
  Description: A project leader adds an activity to an initialized project
  Actors: project leader

  #backgrounds
  Background: There is a developer, a project and an activity in the system and the developer is the project leader
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And The project is initialized

  #Main scenario :
  Scenario: Activity is successfully added to an initialized project
    Given the project leader is the active user
    When the activity with name "Coding" is added to the project
    Then the activity with name "Coding" is in the project


  # Fail scenario :
  Scenario: Activity is unsuccessfully added to an initialized project
    Given There is a Developer with first name "Birte" and last name "Rasmussen"
    And the developer is added to the system
    When The developer is set as the active developer
    Given the project leader is not the active user
    When the activity with name "Coding" is added to the project
    Then an error message "Only the project leader has access to add activities when the project is initialized" is given
