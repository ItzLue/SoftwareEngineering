# Loui
Feature: Create a project
  Description: A developer creates a project
  Actors: Developer

# Main scenario
  Scenario: A developer creates and registers a project
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then the project is registered in the system
    And The project ID fits the current date
