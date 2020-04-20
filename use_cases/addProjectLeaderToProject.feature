Feature: Add project leader to project
  Description: A developer is being added as project leader
  Actors: Developer

#  Background: There is a developer and a project registered in the system
#    Given The following developer(s) is/are registered in the system
#      | OLSM | Ole Smith|
#      | BIRA | Birte Rasmussen |
#    Given A project with ID "030901" is created
#    When The project with ID "030901" is added to the system
#    Then There is a project with ID "030901" in the system
#
#  # Main scenario
#  Scenario: A developer is succesfully being added as project leader to a project
#    When the developer with ID "OLSM" and name "Ole Smith" is set as project leader for project with ID "030901"
#    Then the project with ID "030901" has the developer with ID "OLSM" and name "Ole Smith" as project leader
#
#    #fail scenario
#  Scenario: A developer tries to be added as project leader to a project but is not contained in the
#    When the developer with ID "OLSM" and name "Ole Smith" is set as project leader for project with ID "030901"
#    Then the project with ID "030901" has the developer with ID "OLSM" and name "Ole Smith" as project leader
#    When the developer with ID "BIRA" and name "Birte Rasmussen" is set as project leader for project with ID "030901"
#    Then The error message "Project already has a project leader" is given
