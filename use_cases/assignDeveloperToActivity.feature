# Regin
Feature: Assign developer to project-activity
  Description: The project leader assigns a developer to an activity in the project
  Actor: Project leader

  Background: There is a developer and a project registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And The project is initialized

    #Main scenario
  Scenario: A project leader assigns a developer to an activty
  When the active developer assigns the developer to the activity
  Then the activity has the developer assigned
  And the developer has the activty on his or hers activity list

  #Fail scenario
  Scenario: A non-project leader tries to assign a developer to an activty
    Given There is a Developer with first name "Birthe" and last name "Rasmussen"
    And the developer is added to the system
    And The developer is set as the active developer
    When the active developer assigns the developer to the activity
    Then an error message "Only the project leader has access to assign developers to activities" is given