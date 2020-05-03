Feature: Change Interval date
  Description: Change start and end date of an interval
  Actors: Developer

  Background: There is a project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
    And the activity with name "Coding" is added to the project

  Scenario: The interval start date is set
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30

  Scenario: The interval end date is set
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35