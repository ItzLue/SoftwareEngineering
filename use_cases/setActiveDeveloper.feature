Feature: the active developer is chosen
  Description: acvite developies
  Actors: Developer

  #background
  Background: There is a developer registered in the system
    Given The following developers are registered in the system
      | Ole | Smith |
      | Hans | Madsen |

  Scenario: A developer is set as the active developer
    When The developer with ID "OLSM" is set as the active developer
    Then the developer with ID "OLSM" is the active developer

  Scenario: the active developer is set as empty
    When The developer with ID "OLSM" is set as the active developer
    Then the developer with ID "OLSM" is the active developer