# Loui
Feature: Assign personal activities
  Description: The developer adds a personal activity
  Actors: Developer

  Scenario: : A developer adds a personal activity
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    Given there is a personal activity with name "Skiferie"
    When the personal activity is added to the developer
    Then the developer contains the personal activity
