Feature: Remove activities from project
  Description: A developer removes an activity from a project
  Actors: Developer

  #background
  Background: There is an uninitialized project in the system
    Given A project with name "Enigma Codebreaker" is created
    When The project is added to the system
    Then There is a project in the system with name "Enigma Codebreaker"
