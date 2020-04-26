Feature: the active developer is chosen
  Description: acvite developies
  Actors: Developer

  #background
  Background: There is a developer registered in the system
    Given There is a Developer with first name "Ole" and last name "Smith"
    When the developer with first name "Ole" and last name "Smith" is added to the system
    Then the developer with ID "OLSM" and first name "Ole" and last name "Smith" is in the system
#    Given The following developers are registered in the system
#      | Hans | Madsen |

  Scenario: A developer is set as the active developer
    When The developer with ID "OLSM" is set as the active developer
    Then the developer with ID "OLSM" is the active developer