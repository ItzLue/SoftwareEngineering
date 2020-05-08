Feature: Add activities to an uninitialized project
  Description: A developer adds an activity to an uninitialized project
  Actors: Developer

  #background
  Background: There is an uninitialized project in the system

    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    And The project has not been initialized
    Given there is an activity with name "Coding"



    #first scenario
  Scenario: Activity is added to an uninitialized project
  When the activity with name "Coding" is added to the project
  Then the activity with name "Coding" is in the project




