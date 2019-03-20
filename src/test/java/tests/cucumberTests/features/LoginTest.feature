Feature: Verify login functionality on slideshare app

  Scenario: User should be able to login on app with correct credentials and initial set up should be shown
    Given User has slideshare "android" app
    And username and password is "valid"
    When user enters credentials
    And taps on "signin" button
    Then "Get Started" button should be visible
    And user should be able to scroll
    And long press the search icon