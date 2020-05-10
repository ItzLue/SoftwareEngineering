# Christian
Feature: Set work hours and planned hours
  Description: A developer sets his/hers worked and planned hours
  Actors: Developer

 #background
  Background: There is a developer, a project and an activity within the project in the system and the developer is
  the project leader and the developer contains the activity
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project
    Given the active developer assigns the developer to the activity
    And the developer has the activty on his or hers activity list


      # Main scenario
  Scenario: A developer set worked hours for the activity with no planned hours
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30
    When The developer sets their worked hours for the activity to 8.0
    Then The developer and activity worked hours are 8.0

      # second scenario
  Scenario: A developer set worked hours for the activity with planned hours
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30
    Given The activity planned hours is set to 10.0
    When The developer sets their worked hours for the activity to 8.0
    Then The developer and activity worked hours are 8.0

     #fail scenario
  Scenario: A developer set invalid worked hours for the activity
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30
    When The developer sets their worked hours for the activity to 0.4
    Then an error message "Please provide a valid input" is given

      #fail scenario
  Scenario: Set worked hours on a not yet started activity
    When The developer sets their worked hours for the activity to 1.0
    Then an error message "The activity is not started yet" is given



