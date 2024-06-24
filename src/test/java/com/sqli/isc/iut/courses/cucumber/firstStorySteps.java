package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.sqli.isc.iut.courses.exceptions.TooManyCustomersInBarException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FirstStorySteps {
  private Bar bar;
  private boolean canEnter;
  private List<Person> futureCustomers = new ArrayList<>();

  @Given("the bar has {int} people")
    public void the_bar_has_people(int numberOfPeople) throws TooManyCustomersInBarException {
        bar = new Bar(10);
        List<Person> presentCustomersGiven = new ArrayList<>();

        // Actual customer
        for (int i = 0; i < numberOfPeople; i++) {
            Person personIsAlreadyPresent = new Person(String.valueOf(i + 1), 50.0, 2);
            presentCustomersGiven.add(personIsAlreadyPresent);
        }
        bar.addCustomers(presentCustomersGiven);

        //Futur customer
        for (int i = 0; i < 2; i++) {
          Person personIsAlreadyPresent = new Person("new " + String.valueOf(i + 1), 50.0, 2);
          futureCustomers.add(personIsAlreadyPresent);
      }
    }

    @When("{int} customers try to enter")
    public void customers_try_to_enter(int numberOfNewCustomers) {
        try {
            bar.addCustomers(futureCustomers);
          
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
        assertEquals(bar.getCustomers().size(), numberOfPeople);
    }

}
