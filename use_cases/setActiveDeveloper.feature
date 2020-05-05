Feature: the active developer is chosen
  Description: A user selects a developer as active developer in the system
  Actors: Developer

  #background
  Background: There is a developer registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    Given a developer hasn't been selected as an active developer


    #Main scenario
  Scenario: A developer is set as the active developer
    When The developer is set as the active developer
    Then the developer is the active developer

