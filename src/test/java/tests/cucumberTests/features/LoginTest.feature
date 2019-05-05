
@login
Feature: Verify login functionality on slideshare app

  Background: Create an instance of android or ios driver before each scenario
    Given User has slideshare "android" app

  Scenario: User should be able to login on app with correct credentials and initial set up should be shown
    Given user has "valid" username and password
    When user enters credentials
    And taps on "signin" button
    Then "Get Started" button should be visible
    And user should be able to scroll
    And long press the search icon
