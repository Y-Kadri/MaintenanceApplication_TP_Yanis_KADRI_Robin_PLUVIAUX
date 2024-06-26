package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sqli.isc.iut.courses.exceptions.TooManyCustomersInBarException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SecondStorySteps {
    private Bar bar;
    private boolean canEnter;
    private boolean canEnterAfter;
    private List<Person> customerLeblancAndPignon = new ArrayList<>();
    List<Person> additionalCustomerAfter = new ArrayList<>();
    Person pignonCustomer;
    Person leblancCustomer;
    double totalBill;
    double pricePerCocktail;
    int nbGlass;

    @Given("second story : the bar employs {int} people, including Leblanc and Pignon")
    public void the_bar_has_people(int numberOfPeople) throws TooManyCustomersInBarException {
        bar = new Bar(10);
        List<Person> presentCustomersGiven = new ArrayList<>();

        // Actual customer
        for (int i = 0; i < numberOfPeople; i++) {
            Person person = new Person(String.valueOf(i + 1), 50.0, 2);
            presentCustomersGiven.add(person);
        }
        bar.addCustomers(presentCustomersGiven);

        //Customer Mr. Pignon and Mr. Leblanc
        pignonCustomer = new Person("Mr. Pignon", 50.0, 1);
        leblancCustomer = new Person("Mr. Pignon", 50.0, 1);
        customerLeblancAndPignon.add(pignonCustomer);
        customerLeblancAndPignon.add(leblancCustomer);

        //Customer after
        Person personAfter = new Person("Customer as well ", 20.0, 1);
        additionalCustomerAfter.add(personAfter);
    }

    @When("second story : {int} customers try to enter")
    public void customers_try_to_enter(int numberOfNewCustomers) {
        try {
            bar.addCustomers(customerLeblancAndPignon);

            canEnter = true;
        } catch (TooManyCustomersInBarException e) {
            canEnter = false;
        }
    }

    @Then("second story : they can enter")
    public void they_can_t_enter() {
        assertTrue(canEnter);
    }

    @Then("second story : the bar has {int} customers")
    public void the_bar_has_customers(int finalCustomers) {
        assertEquals(bar.getCustomers().size(), finalCustomers);
    }

    @When("second story : the next customer tries to enter")
    public void next_customer_tries_to_enter() throws TooManyCustomersInBarException {
        try {
            bar.addCustomers(additionalCustomerAfter);
            canEnterAfter = true;
        } catch (TooManyCustomersInBarException e) {
            canEnterAfter = false;
        }
    }

    @Then("second story : they cannot enter")
    public void they_cannot_enter() {
        assertFalse(canEnterAfter);
    }

    @Then("second story : the bar still has {int} customers")
    public void the_barStill_has_customers(int finalCustomers) {
        assertEquals(bar.getCustomers().size(), finalCustomers);
    }


    @When("second story : {int} customers each order {int} cocktail of the month at {int}€")
    public void customers_each_order_a_cocktail_of_the_month_at(int numberOfCustomers, int nbGlass, int pricePerCocktail) throws TooManyCustomersInBarException {
        this.pricePerCocktail = pricePerCocktail;
        this.nbGlass = nbGlass;

        totalBill = bar.command(customerLeblancAndPignon, pricePerCocktail, leblancCustomer, nbGlass);
    }

    @Then("second story : the total bill is {int}€")
    public void the_total_bill_is(int totalBill) {
        assertEquals(totalBill, leblancCustomer.getAmountToPay());
    }


    @When("second story : Mr. Leblanc pays the bill")
    public void mr_Leblanc_pays_the_bill() {
        leblancCustomer.PayingTheBill();
    }

    @Then("second story : Mr. Pignon is happy because he drank only {int} glass")
    public void mr_Pignon_is_happy_because_he_drank_only_glass(int glassesConsumed) {
        assertEquals(glassesConsumed, pignonCustomer.getGlassConsumed());
        assertTrue(leblancCustomer.getGlassConsumed() <= leblancCustomer.getMaxDrinks());
    }
}