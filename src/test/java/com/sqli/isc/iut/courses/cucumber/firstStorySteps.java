package com.sqli.isc.iut.courses.cucumber;

import java.util.List;

import io.cucumber.java.en.Given;

class Bar {
  private int places;
  private List<String> customers;

  public Bar(int places) {
    this.places = places;
  }

  public int getPlaces() {
    return places;
  }

  public List<String> getCustomers() {
    return customers;
  }

}

public class firstStorySteps {
  private Bar bar;

  @Given("^a bar I just entered with 10 places$")
  public void setup() {
    bar = new Bar(10);
  }

  @Given("^(\\d+) customers enter but there is only (\\d+) place left$")
  public void addCustomers(int arg1, int arg2) {
    // fill the bar with arg2 customers
    int numberOfCustomer = bar.getPlaces() - arg2;

    for (int i = 0; i < numberOfCustomer; i++) {
      bar.getCustomers().add("customer" + i);
    }

    // add arg1 customers to the bar
    for (int i = 0; i < arg1; i++) {
      bar.getCustomers().add("customer" + i);
    }
  }

  @Given("^they can't enter$")
  public void they_cant_enter() throws Throwable{
  }

}
