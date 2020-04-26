Feature: Add activities to project
  Description: A developer creates a project
  Actors: Developer

  #background
  Background: There is a developer registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer with first name "Ole" and last name "Smith" is added to the system
    Then the developer with ID "OLSM" and first name "Ole" and last name "Smith" is in the system
    Given The following developers are registered in the system
      | Hans | Madsen |

    #first scenario
  Scenario: A developer creates a project with a name, activities, and start/end dates.
    Given the following activities have been chosen for the project
      | Coding | 10 | 14 | 16 |
      | Bug fixing | 12| 7 | 10 |
      | customer talk | 15 | 4 | 5 |
    When A new project with ID "030901" and name "importantProject", start date 10/5/20, end date 30/5/20 and the given activities is created
    When The project with ID "030901" is added to the system
    Then There is a project with ID "030901" in the system

    #second fail scenario
  Scenario: A developer creates a project with invalid start/end dates for one or more of the activities
    Given the following activities have been chosen for the project
      | Coding | 10 | 14 | 16 |
      | Bug fixing | 12| 7 | 10 |
      | customer talk | 15 | 4 | 5 |
    When A new project with ID "030901" and name "importantProject", start date 10/5/20, end date 30/5/20 and the given activities is created
    And One or more of the activity start/end dates are not within the project start/end dates
    Then The error message "Invalid activity dates" is given

    #fourth scenario
  Scenario: A developer creates a project with activities.
    Given the following activities have been chosen for the project
      | Coding | 10 | 14 | 16 |
      | Bug fixing | 12| 7 | 10 |
      | customer talk | 15 | 4 | 5 |
    When A new project with ID "030901" and and the given activities is created
    When The project with ID "030901" is added to the system
    Then There is a project with ID "030901" in the system

