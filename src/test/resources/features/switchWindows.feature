Feature: switch windows


    
  Scenario: Create and switch windows
    Given  SetUP and open URL: "https://uk.wikipedia.org/wiki/"
    When User opens a new window with URL "https://uk.wikipedia.org/wiki/Java"
    Then User should be on the new window
    When  User opens a new window with URL "https://uk.wikipedia.org/wiki/Selenium"
    Then User should be on the new window
    When User switch to initial window
    Then User should be on initial window