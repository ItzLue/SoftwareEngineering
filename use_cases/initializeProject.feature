#Feature: someone initializes a project
#  Description: a project is initialized
#  Actors: Developer
#
#    #background
#  Background: There is a project registered in the system
#    Given A project with name "Enigma Codebreaker" is created
#    When The project is added to the system
#    Then the project is registered in the system
#
#  #Main Scenario
#  Scenario: A project is initialized
#    Given The project has not been initialized
#    When The project is initialized by the user
#    Then The project is initialized
