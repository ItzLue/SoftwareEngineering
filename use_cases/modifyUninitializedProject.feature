Feature: Change project name, dates, and activities in the project
  Description: A developer changes an uninitialized project
  Actors: Developer

  Background: There is an uninitialized project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then the project is registered in the system
    When there is an activity with name "Coding"
    Then the activity with name "Coding" is added to the project
    And The project has not been initialized


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
    Then An exception is expected
    When The start date of the project is set to year 2020 and week 40
    Then An exception is given

  Scenario: The project start date is set and the end date is not null and not valid
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30
    Then An exception is expected
    When The end date of the project is set to year 2020 and week 25
    Then An exception is given

  Scenario: The activity start date is set and the end date is null
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30

  Scenario: The activity end date is set and the start date is null
    When The end date of the activity is set to year 2020 and week 30
    Then The activity has the ending year 2020 and the ending week 30

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
    Then An exception is expected
    When The start date of the activity is set to year 2020 and week 34
    Then An exception is given

  Scenario: An invalid activity end date is set
    When The start date of the activity is set to year 2020 and week 32
    Then The activity has the starting year 2020 and the starting week 32
    Then An exception is expected
    When The end date of the activity is set to year 2020 and week 31
    Then An exception is given


#  Scenario: The interval end date is set and then the start date is set to after the end date
#    When The end date of the project is set to year 2020 and week 35
#    Then The project has the ending year 2020 and the ending week 35
#    And The start date of the project is set to year 2020 and week 40
#    Then The error message "invalid date" is given

#  Scenario: The interval start date is set and then the end date is set to before the start date
#    When The start date of the project is set to year 2020 and week 30
#    Then The project has the starting year 2020 and the starting week 30
#    When The end date of the project is set to year 2020 and week 25
#    Then The error message "invalid date" is given

#  Scenario: For an activity in the project, start date is set
#    When The start date of the activity is set to year 2020 and week 30
#    Then The activity has the starting year 2020 and the starting week 30
#
#  Scenario: For an activity in the project, end date is set
#    When The end date of the activity is set to year 2020 and week 35
#    Then The activity has the ending year 2020 and the ending week 35

  #hvad kan man gøre
  #ændre navn
  #ændre datoer
  #ændre på activiteterne i projektet: datoer, navn, planned hours

