Feature: someone initializes a project
  Description: a project is initialized
  Actors: Developer

    #background
  Background: There is a project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
    Given The following developers are registered in the system
      | Ole | Smith |

  #Main Scenario
  Scenario: A project is initialized
    Given The project has not been initialized
    When The project is initialized by the user
    Then The project is initialized