Feature: Change project name, dates, and activities in the project
  Description: A developer changes an uninitialized project
  Actors: Developer

  Background: There is an uninitialized project registered in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
    Then the activity with name "Coding" is added to the project
    And The project has not been initialized


  Scenario: The project name is changed
    When The name of the project is changed to "Maintenance Work"
    Then There is a project in the system with name "Maintenance Work"


  Scenario: The name of an activity is changed
    When The name of the activity with name "Coding" is changed to "Development"
    Then the activity with name "Development" is in the project
  #hvad kan man gøre
  #ændre navn
  #ændre datoer
  #ændre på activiteterne i projektet: datoer, navn, planned hours

