# Christian
Feature: Change project name, dates, and activities in the project
  Description: A developer changes an uninitialized project
  Actors: Developer

  Background: There is an uninitialized project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    And The project is added to the system
    And The project has not been initialized
    Given there is an activity with name "Coding"
    And the activity with name "Coding" is added to the project


  Scenario: The project name is changed
    When The name of the project is changed to "Maintenance Work"
    Then There is a project registered in the system with name "Maintenance Work"

  Scenario: The project start date is set and the end date is null
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30

  Scenario: The project end date is set and the start date is null
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35

  Scenario: The project start date is set and the end date is not null but valid
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30

  Scenario: The project end date is set and the start date is not null but valid
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35

  Scenario: The project start date is set and the end date is not null and not valid
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35
    When The start date of the project is set to year 2020 and week 40
    Then an error message "Invalid date" is given

  Scenario: The project start date is set and the end date is not null and not valid
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30
    When The end date of the project is set to year 2020 and week 25
    Then an error message "Invalid date" is given

  Scenario: The activity start date is set and the end date is null
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30

  Scenario: The activity end date is set and the start date is null
    When The end date of the activity is set to year 2020 and week 30
    Then The activity has the ending year 2020 and the ending week 30

  Scenario: set start date for activity that doesnt exist
    When The start date of a nonexistent activity is set
    Then an error message "Project doesn't exist" is given

  Scenario: The activity start date is set and the end date is not null
    When The end date of the activity is set to year 2020 and week 32
    Then The activity has the ending year 2020 and the ending week 32
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30

  Scenario: The activity end date is set and the start date is not null
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30
    When The end date of the activity is set to year 2020 and week 35
    Then The activity has the ending year 2020 and the ending week 35

  Scenario: The activity start date is set and the project start date is not null
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30

  Scenario: The activity end date is set and the project end date is not null
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35
    When The end date of the activity is set to year 2020 and week 35
    Then The activity has the ending year 2020 and the ending week 35

  Scenario: An invalid activity start date is set
    When The end date of the activity is set to year 2020 and week 32
    Then The activity has the ending year 2020 and the ending week 32
    When The start date of the activity is set to year 2020 and week 34
    Then an error message "Invalid date" is given

  Scenario: An invalid activity end date is set
    When The start date of the activity is set to year 2020 and week 32
    Then The activity has the starting year 2020 and the starting week 32
    When The end date of the activity is set to year 2020 and week 31
    Then an error message "Invalid date" is given

  Scenario: The project start date is set but the activity start date is before that
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30
    When The start date of the project is set to year 2020 and week 32
    Then an error message "Activity start dates are before the set date" is given

  Scenario: The project end date is set but the activity end date is after that
    When The end date of the activity is set to year 2020 and week 32
    Then The activity has the ending year 2020 and the ending week 32
    When The end date of the project is set to year 2020 and week 30
    Then an error message "Activity end dates are after the set date" is given

  Scenario: Set date for projectID that doesnt exist
    When The start date of a project that doesnt exist is set
    Then an error message "Project doesn't exist" is given
