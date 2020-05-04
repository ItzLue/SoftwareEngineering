Feature: Create a project
  Description: A developer creates a project
  Actors: Developer

# Main scenario
  Scenario: A developer creates and registers a project
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then the project is registered in the system

    #second scenario
  Scenario: The project ID fits the current date
    Given A project with name "Enigma Codebreaker" is created
    Then The project is added to the system
    Then The project ID fits the current date





