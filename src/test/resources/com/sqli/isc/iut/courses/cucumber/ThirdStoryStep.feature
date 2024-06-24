Feature: Third story - Cocktail orders and payments in a limited capacity bar

  Background: the bar employs 10 people, including Leblanc and Pignon
    Given third story : the bar employs 3 people, including Leblanc and Pignon

  Scenario: Customers arrive and order cocktails without paying for each other
    When third story : 2 customers try to enter
    Then third story : they can enter
    And third story : the bar has 5 customers

    When third story : 2 customers each order 1 cocktail of the month at 10€ without anyone paying for the other
    Then third story : each customer has a bill of 10€

    When third story : Mr. Pignon pays his bill
    And third story: Mr Leblanc insists on paying for 1 more cocktail of the month, so Mr Leblanc orders 2 more cocktails of the month at 10€
    Then third story : Mr. Leblanc's total bill is 30€
    
    When third story : Mr. Leblanc pays the bill
    Then third story : Mr. Pignon is sad because he drank more than 1 glass
