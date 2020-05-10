# Christian
Feature: Change project name
  Description: A developer tries to change an initialized project
  Actors: Developer

  Background: There is an uninitialized project and an activity in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And The project is initialized


  Scenario: The developer tries to change the project date without being the active developer
    When The start date of the project is set to year 2020 and week 30
    Then an error message "You don't have access" is given

  Scenario: The developer tries to set planned hours for the activity
    When The activity planned hours is set to 10.0
    Then an error message "You don't have access" is given

  Scenario: The developer tries to set date for the activity
    When The start date of the project is set to year 2020 and week 30
    Then an error message "You don't have access" is given
