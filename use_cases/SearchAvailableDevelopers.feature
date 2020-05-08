Feature: Search for available developers
  Description: A projectleader searches for available developers for an activity
  Actors: Project leader

  Background: There is a developer and a project registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And The project is initialized

    #main scenario
  Scenario: The projectleader searches for available developers
    And The developer is set as the active developer
    When The project leader searches for available developers for the project and activity

  Scenario: The projectleader searches for available developers
    When The project leader searches for available developers for the project and activity
    Then an error message "Only the project leader may search for available developers" is given