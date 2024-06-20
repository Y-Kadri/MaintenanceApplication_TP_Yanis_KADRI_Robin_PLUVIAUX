Feature: A bar with a limited number of places

  Background: A bar
    Given a bar I just entered with 10 places and 9 customers

  Scenario: New customers
    When 2 customers enter
    Then they can't enter
