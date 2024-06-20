package com.sqli.isc.iut.courses.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class firstStorySteps {
  private Bar bar;
  private int numberOfDefaultCustomers;
  private boolean canEnter;

  @Given("a bar I just entered with {int} places and {int} customers")
  public void setup(int arg1, int arg2) {
    this.numberOfDefaultCustomers = arg2;
    bar = new Bar(arg1);
    List<String> customers = new ArrayList<String>();

    // fill the bar with 9 customers
    for (int i = 0; i < arg2; i++) {
      customers.add("customer" + i);
    }
    bar.addCustomers(customers);
  }

  @When("^(\\d+) customers enter$")
  public void addCustomers(int arg1) {
    List<String> customers = new ArrayList<String>();

    // fill the bar with arg1 customers
    for (int i = 0; i < arg1; i++) {
      customers.add("toto" + i);
    }
    this.canEnter = bar.addCustomers(customers);
  }

  @Given("^they can't enter$")
  public void they_cant_enter() {
    // the number of customers in the bar stays the same
    assertEquals(numberOfDefaultCustomers, bar.getCustomers().size());
    assertEquals(false, this.canEnter);
  }

}
