# Christian
Feature: Modify project
  Description: A developer tries to change an initialized project'
  Actors: Developer

  Background: There is a developer, a project and an activity in the system and the developer is the project leader
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And The project is initialized

      #Fail scenario
  Scenario: The developer tries to change the project date without being set as the active developer
    When The start date of the project is set to year 2020 and week 30
    Then an error message "You don't have access" is given

          #Fail scenario
  Scenario: The developer tries to set planned hours for the activity withut being set as the active developer
    When The activity planned hours is set to 10.0
    Then an error message "You don't have access" is given

      #Fail scenario
  Scenario: The developer tries to set date for the activity without being set as the active developer
    When The start date of the project is set to year 2020 and week 30
    Then an error message "You don't have access" is given
