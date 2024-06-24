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

public class ThirdStorySteps {
    private Bar bar;
    private boolean canEnter;
    private List<Person> customerLeblancAndPignon = new ArrayList<>();
    Person pignonCustomer;
    Person leblancCustomer;
    double pricePerCocktail;
    double leblancBill;
    double pignonBill;
    
    @Given("third story : the bar employs {int} people, including Leblanc and Pignon")
    public void the_bar_has_people(int numberOfPeople) throws TooManyCustomersInBarException {
        bar = new Bar(10);
        List<Person> presentCustomersGiven = new ArrayList<>();

        // Actual customer
        for (int i = 0; i < numberOfPeople; i++) {
            Person person = new Person(String.valueOf(i + 1), 50.0, 2);
            presentCustomersGiven.add(person);
        }
        bar.addCustomers(presentCustomersGiven); //TODO mettre dans l'attribut de classe directement

        // Customer Mr. Pignon and Mr. Leblanc
        pignonCustomer = new Person("Mr. Pignon", 50.0, 1);
        leblancCustomer = new Person("Mr. Leblanc", 50.0, 3);
        customerLeblancAndPignon.add(pignonCustomer);
        customerLeblancAndPignon.add(leblancCustomer);
    }

    @When("third story : {int} customers try to enter")
    public void customers_try_to_enter(int numberOfNewCustomers) {
        try {
            bar.addCustomers(customerLeblancAndPignon);
            canEnter = true;
        } catch (TooManyCustomersInBarException e) {
            canEnter = false;
        }
    }

    @Then("third story : they can enter")
    public void they_can_enter() {
        assertTrue(canEnter);
    }

    @Then("third story : the bar has {int} customers")
    public void the_bar_has_customers(int finalCustomers) {
        assertEquals(bar.getCustomers().size(), finalCustomers);
    }

    @When("third story : {int} customers each order {int} cocktail of the month at {int}€ without anyone paying for the other")
    public void customers_each_order_a_cocktail_of_the_month_at(int numberOfCustomers, int nbGlass, int pricePerCocktail) throws TooManyCustomersInBarException {        
        this.pricePerCocktail = pricePerCocktail;
        
        // Each customer pays for their own drinks
        bar.command(customerLeblancAndPignon, pricePerCocktail, null, nbGlass);
    }

    @Then("third story : each customer has a bill of {int}€")
    public void each_customer_has_a_bill_of(int totalBill) {
        assertEquals(totalBill, pignonCustomer.getAmountToPay());
        assertEquals(totalBill, leblancCustomer.getAmountToPay());
    }

    @When("third story : Mr. Pignon pays his bill")
    public void mr_Pignon_pays_his_bill() {
        pignonCustomer.PayingTheBill();
    }

    @When("third story: Mr Leblanc insists on paying for {int} more cocktail of the month, so Mr Leblanc orders 2 more cocktails of the month at {int}€")
    public void mr_Leblanc_insists_on_paying_for_more_cocktails(int nbCoktail, int pricePerCocktail) {
        // Mr. Leblanc orders 2 more cocktails
        bar.command(customerLeblancAndPignon, pricePerCocktail, leblancCustomer, nbCoktail);
    }

    @Then("third story : Mr. Leblanc's total bill is {int}€")
    public void mr_Leblanc_total_bill_is(int totalBill) {
        assertEquals(totalBill, leblancCustomer.getAmountToPay());
    }

    @When("third story : Mr. Leblanc pays the bill")
    public void mr_Leblanc_pays_the_bill() {
        leblancCustomer.PayingTheBill();
    }

    @Then("third story : Mr. Pignon is sad because he drank more than {int} glass")
    public void mr_Pignon_is_sad_because_he_drank_more_than_glass(int glassesConsumed) {
        assertTrue(pignonCustomer.getGlassConsumed() > glassesConsumed);
    }
}
