Feature: A bar with a limited number of places

  Scenario: New customers
    Given the bar has 9 people
    When 2 customers try to enter
    Then they can't enter
    And the bar still has 9 customers
