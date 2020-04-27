Feature: Create a project
  Description: A developer creates a project
  Actors: Developer

# Main scenario
  Scenario: A developer creates a project
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"

    #second scenario
  Scenario: The project ID fits the current date
    Given A project with name "Enigma Codebreaker" is created
    Then The project is added to the system
    Then The project ID fits the current date





