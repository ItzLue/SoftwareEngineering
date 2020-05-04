Feature: Add activities to an initialized project
  Description: A developer adds an activity to an initialized project
  Actors: Developer

  #backgrounds
  Background: There is an uninitialized project and an activity in the system
    Given A project with name "Enigma Codebreaker" is created
    And there is an activity with name "Coding"
    When The project is added to the system
    Then the project is registered in the system

    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    When The developer is set as the active developer

    Given the developer is set as project leader for project with name "Enigma Codebreaker"

  #first scenario
  Scenario: Activity is succesfully added to an initialized project
  #  Given the project leader is the active user
    When the activity with name "Coding" is added to the project
    Then the activity with name "Coding" is in the project


  # Fail scenario :
