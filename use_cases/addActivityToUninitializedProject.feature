# Joachim
Feature: Add activities to an uninitialized project
  Description: A developer adds an activity to an uninitialized project
  Actors: Developer

  #background
  Background: There is an uninitialized project and an activity in the system
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    And The project has not been initialized
    Given there is an activity with name "Coding"

    #first scenario :
  Scenario: Activity is added succesfully to an uninitialized project
  When the activity with name "Coding" is added to the project
  Then the activity with name "Coding" is in the project

   #fail scenario :
  Scenario: Activity is unsuccesfully added to an uninitialized project
    When the activity with name "Coding" is added to the project
    Then the activity with name "Coding" is in the project
    When the activity with name "Coding" is added to the project
    Then an error message "Not a valid name" is given

