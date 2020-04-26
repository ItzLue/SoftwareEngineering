Feature: Set interval
  Description: the interval of a project or an activity is set
  Actors: Developer

  Background: There is a project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"

  Scenario: Project interval is set
