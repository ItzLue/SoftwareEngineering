Feature: someone initializes a project
  Description: a project is initialized
  Actors: Developer

    #background
  Background: There is a project registered in the system
    Given A project is created
    When The project is added to the system
    Then There is a project in the system
#    Given The following developers are registered in the system
#      | Hans | Madsen |

  #Main Scenario
  Scenario: A project is initialized
    Given The project has not been initialized
    When The project is initialized by the user
    Then The project is initialized