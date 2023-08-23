Feature: Search test

  Scenario Outline: Parallel Product Search

    Given SetUP and open URL: "<url>"
    Given User opens second browser for URL: "<second url>"
    When User find in search box "<item>"
    When User find in second browser search box "<item>"
    Then Check all items contains: "<item>" on "<site>"
    Then Check all items contains: "<item>" on "<secondSite>"
    Examples:
      | url                     | item   | second url              | site   | secondSite |
      | https://www.amazon.com/ | iPhone | https://rozetka.com.ua/ | amazon | rozetka    |
