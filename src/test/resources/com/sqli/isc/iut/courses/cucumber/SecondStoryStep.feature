Feature: A bar with a limited number of places and cocktail orders

  Background: the bar employs 10 people, including Leblanc and Pignon
    Given second story : the bar employs 8 people, including Leblanc and Pignon

  Scenario: Customers arrive at the bar
    When second story : 2 customers try to enter
    Then second story : they can enter
    And second story : the bar has 10 customers

    When second story : the next customer tries to enter
    Then second story : they cannot enter
    And second story : the bar still has 10 customers

    When second story : 2 customers each order 1 cocktail of the month at 10€
    Then second story : the total bill is 20€

    When second story : Mr. Leblanc pays the bill
    Then second story : Mr. Pignon is happy because he drank only 1 glass