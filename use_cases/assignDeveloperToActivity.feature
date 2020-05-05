Feature: Assign developer to project-activity
  Description: The project leader assigns a developer to an activity in the project
  Actor: Project leader

  Background: There is a developer and a project registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    And the developer is added to the system
    And The developer is set as the active developer
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project
    Given the developer is set as project leader for project with name "Enigma Codebreaker"
    And The project is initialized

    #Main scenario
  Scenario: A project leader assigns a developer to an activty
  When the active developer assigns the developer to the activity
  Then the activity has the developer assigned
  And the developer has the activty on his or hers activity list

  #Fail scenario
  Scenario: A non-project leader tries to assign a developer to an activty
    Given There is a Developer with first name "Birthe" and last name "Rasmussen"
    And the developer is added to the system
    And The developer is set as the active developer
    When the active developer assigns the developer to the activity
    Then the error message "You don't have access" is given












#  Background: There is a developer and a project registered in the system
#    Given The following developer(s) is/are registered in the system
#      | OLSM | Ole Smith|
#      | BIRA | Birte Rasmussen |
#    Given the following activities have been chosen for the project
#      | Coding | 10 | 14 | 16 |
#      | Bug fixing | 12| 7 | 10 |
#      | customer talk | 15 | 4 | 5 |
#    When A new project with ID "030901" and and the given activities is created
#    When The project with ID "030901" is added to the system
#    Then There is a project with ID "030901" in the system
#    When the developer with ID "OLSM" and name "Ole Smith" is set as project leader for project with ID "030901"
#    Then the project with ID "030901" has the developer with ID "OLSM" and name "Ole Smith" as project leader
#
#  # Main scenario
#  Scenario: A project leader assign a developer in a project to an activity
#    Given the developer with ID "BIRA" and name "Birte Rasmussen" has 19 activities per week in the duration of the activity
#    When project leader adds developer with ID "BIRA" and name "Birte Rasmussen" to activity with name "Coding" BT 10 start week 14 and end week 16
#    Then the activity with name "Coding" BT 10 start week 14 and end week 16 contains the developer with ID "BIRA" and name "Birte Rasmussen"
#
#  # Fail scenario
#  Scenario: A project leader assign a developer in a project to an activity
#    Given the developer with ID "BIRA" and name "Birte Rasmussen" has 20 activities per week in the duration of the activity
#    When project leader adds developer with ID "BIRA" and name "Birte Rasmussen" to activity with name "Coding" BT 10 start week 14 and end week 16
#    Then the error message "Can not add more activities to the developer" is given