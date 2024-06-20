Feature: A bar with a limited number of places

  Background: A bar
    Given a bar I just entered with 10 places

  Scenario: New customers
    When 2 customers enter but there is only 1 place left
    Then they can't enter