Feature: Add project leader to project
  Description: A developer is being added as project leader
  Actors: Developer

  Background: There is a developer and a project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
    Given The following developers are registered in the system
      | Ole | Smith |

   #Main scenario
  Scenario: A developer is successfully being added as project leader to a project
    When the developer with first name "Ole" and last name "Smith" is set as project leader for project with name "Enigma Codebreaker"
    Then the project with name "Enigma Codebreaker" has the developer with first name "Ole" and last name "Smith" as project leader

    #fail scenario
#  Scenario: A developer tries to be added as project leader to a project already has a developer
#    When the developer with first name "Ole" and last name "Smith" is set as project leader for project with name "Enigma Codebreaker"
#    Then the project with name "Enigma Codebreaker" has the developer with first name "Ole" and last name "Smith" as project leader
#    When the developer with first name "Birte" and last name "Rasmussen" is set as project leader for project with name "Enigma Codebreaker"
#    Then The error message "Project already has a project leader" is given
