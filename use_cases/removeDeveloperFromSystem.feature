# Regin
Feature: Remove a developer from the system
  Description: A developer is removed from the system
  Actors: Developer

   Background:There is a developer, a project and an activity in the system and the developer contains the activity
     Given There is a Developer with first name "Ole" and last name "Smith"
     And the developer is added to the system
     And The developer is set as the active developer
     Given A project with name "Enigma Codebreaker" is created
     And The project is added to the system
     Given there is an activity with name "Coding"
     And the activity with name "Coding" is added to the project
     And the active developer assigns the developer to the activity

      # Main scenario
   Scenario: The developer, and all the pointers to him is being removed
     When the developer is being removed from the system
     Then the developer is not contained in the system
     And the activty does not contain the developer