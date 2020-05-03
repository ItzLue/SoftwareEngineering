Feature: Add project leader to project
  Description: A developer is being added as project leader
  Actors: Developer

  Background: There is a developer and a project registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer with first name "Ole" and last name "Smith" is added to the system
    Then the developer with first name "Ole" and last name "Smith" and appropriate ID is in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
    Given The project has not been initialized
    When The project is initialized by the user
    Then The project is initialized

        #Main scenario
  Scenario: A developer is successfully being added as project leader to a project
    When the developer with first name "Ole" and last name "Smith" is set as project leader for project with name "Enigma Codebreaker"
    Then the project with name "Enigma Codebreaker" has the developer with first name "Ole" and last name "Smith" as project leader

        #Second scenario
  Scenario: A developer is successfully added as project leader to a project with an existing project leader
    Given the developer with first name "Ole" and last name "Smith" is set as project leader for project with name "Enigma Codebreaker"
    And a developer with first name "Birte" and last name "Rasmussen" is added to the system
    When the developer with first name "Birte" and last name "Rasmussen" is set as project leader for project with name "Enigma Codebreaker"
    Then the project with name "Enigma Codebreaker" has the developer with first name "Birte" and last name "Rasmussen" as project leader