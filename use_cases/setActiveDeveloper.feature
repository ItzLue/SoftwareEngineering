Feature: the active developer is chosen
  Description: A user selects a developer as active developer in the system
  Actors: Developer

  #background
  Background: There is a developer registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer with first name "Ole" and last name "Smith" is added to the system
    Then the developer with first name "Ole" and last name "Smith" and appropriate ID is in the system

    #Main scenario
  Scenario: There isn't chosen an active developer
    When a developer isn't selected as an active developer
    Then the active developer variable has the value null

    #Second scenario
  Scenario: A developer is set as the active developer
    When The developer with first name "Ole" and last name "Smith" is set as the active developer
    Then the developer with first name "Ole" and last name "Smith" is the active developer


