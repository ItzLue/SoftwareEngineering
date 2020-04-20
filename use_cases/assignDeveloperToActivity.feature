Feature: Assign developer to project-activity
  Description: The project leader assigns a developer to an activity in the project
  Actor: Project leader

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