Feature: Add a developer to the system
  Description: A developer is added to the system
  Actors: Developer

  # Main scenario
  Scenario: A developer is successfully added to the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer is added to the system
    Then the developer is in the system with an appropriate ID

  Scenario: print all developers
    When All developers are printed

