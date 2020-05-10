Feature: removing a personal activity
  Description: A developer removes a personal activity
  Actors: Developer

  Background:
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    Given there is a personal activity with name "Skiferie"
    And the personal activity is added to the developer

    Scenario:
      When the developer removes the personal activity
      Then the personal activity is not contained in the developer