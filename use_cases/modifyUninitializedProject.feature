Feature: Change project name, dates, and activities in the project
  Description: A developer changes an uninitialized project
  Actors: Developer

  Background: There is an uninitialized project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
    When there is an activity with name "Coding"
    Then the activity with name "Coding" is added to the project
    And The project has not been initialized


  Scenario: The project name is changed
    When The name of the project is changed to "Maintenance Work"
    Then There is a project in the system with name "Maintenance Work"


  Scenario: The interval start date is set
    When The start date of the project is set to year 2020 and week 30
    Then The project has the starting year 2020 and the starting week 30

  Scenario: The interval end date is set
    When The end date of the project is set to year 2020 and week 35
    Then The project has the ending year 2020 and the ending week 35

  Scenario: For an activity in the project, start date is set
    When The start date of the activity is set to year 2020 and week 30
    Then The activity has the starting year 2020 and the starting week 30

  Scenario: For an activity in the project, end date is set
    When The end date of the activity is set to year 2020 and week 35
    Then The activity has the ending year 2020 and the ending week 35

  #hvad kan man gøre
  #ændre navn
  #ændre datoer
  #ændre på activiteterne i projektet: datoer, navn, planned hours

