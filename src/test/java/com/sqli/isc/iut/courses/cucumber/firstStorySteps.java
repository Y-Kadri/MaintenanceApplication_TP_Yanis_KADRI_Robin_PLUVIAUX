package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.sqli.isc.iut.courses.exceptions.TooManyCustomersInBarException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class firstStorySteps {
  private Bar bar;
  private boolean canEnter;
  List<String> futurCustomers = new ArrayList<>();

  @Given("the bar has {int} people")
    public void the_bar_has_people(int numberOfPeople) throws TooManyCustomersInBarException {
        bar = new Bar(10);
        List<String> presentCustomersGiven = new ArrayList<>();

        // Actual customer
        for (int i = 0; i < numberOfPeople; i++) {
            presentCustomersGiven.add(String.valueOf(i + 1));
        }
        bar.addCustomers(presentCustomersGiven);

        //Futur customer
        for (int i = 0; i < 2; i++) {
          futurCustomers.add("new " + String.valueOf(i + 1));
      }
    }

    @When("{int} customers try to enter")
    public void customers_try_to_enter(int numberOfNewCustomers) {
        try {
            bar.addCustomers(futurCustomers);
          
            canEnter = true;
        } catch (TooManyCustomersInBarException e) {
            canEnter = false;
        }
    }

    @Then("they can't enter")
    public void they_can_t_enter() {
        assertFalse(canEnter);
    }

    @Then("the bar still has {int} customers")
    public void the_bar_is_fully_booked(int numberOfPeople) {
        assertEquals(bar.getNumberOfCustomers(), numberOfPeople);
    }

}
