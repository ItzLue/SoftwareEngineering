Feature: Add a developer to the system
  Description: A developer is added to the system
  Actors: Developer

  # Main scenario
  Scenario: A developer is succesfully added to the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer with first name "Ole" and last name "Smith" is added to the system
    Then the developer with ID "OLSM" and first name "Ole" and last name "Smith" is in the system



