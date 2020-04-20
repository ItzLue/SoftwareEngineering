Feature: Create a project
  Description: A developer creates a project
  Actors: Developer

  #background
  Background: There is a developer registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer with first name "Ole" and last name "Smith" is added to the system
    Then the developer with ID "OLSM" and first name "Ole" and last name "Smith" is in the system
#    Given The following developers are registered in the system
#      | Hans | Madsen |

# Main scenario
  Scenario: A developer creates a project
    Given A project with name "Enigma Codebreaker " is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"

#    #Main fail scenario
#  Scenario: A developer creates a project with an already used ID
#    Given A project with ID "030901" is created
#    When The project with ID "030901" is added to the system
#    And There is a project with ID "030901" in the system
#    When A project with ID "030901" is created
#    Then The error message "project ID already used" is given
#
#    #second scenario
#  Scenario: A developer creates a project with a name, activities, and start/end dates.
#    Given the following activities have been chosen for the project
#      | Coding | 10 | 14 | 16 |
#      | Bug fixing | 12| 7 | 10 |
#      | customer talk | 15 | 4 | 5 |
#    When A new project with ID "030901" and name "importantProject", start date 10/5/20, end date 30/5/20 and the given activities is created
#    When The project with ID "030901" is added to the system
#    Then There is a project with ID "030901" in the system
#
#    #second fail scenario
#  Scenario: A developer creates a project with invalid start/end dates for one or more of the activities
#    Given the following activities have been chosen for the project
#      | Coding | 10 | 14 | 16 |
#      | Bug fixing | 12| 7 | 10 |
#      | customer talk | 15 | 4 | 5 |
#    When A new project with ID "030901" and name "importantProject", start date 10/5/20, end date 30/5/20 and the given activities is created
#    And One or more of the activity start/end dates are not within the project start/end dates
#    Then The error message "Invalid activity dates" is given
#
#    #fourth scenario
#  Scenario: A developer creates a project with activities.
#    Given the following activities have been chosen for the project
#      | Coding | 10 | 14 | 16 |
#      | Bug fixing | 12| 7 | 10 |
#      | customer talk | 15 | 4 | 5 |
#    When A new project with ID "030901" and and the given activities is created
#    When The project with ID "030901" is added to the system
#    Then There is a project with ID "030901" in the system






