package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

import com.sqli.isc.iut.courses.exceptions.TooManyCustomersInBarException;

public class Bar {

    private int numberOfPlaces;
    private List<Person> customers;

    public Bar(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
        this.customers = new ArrayList<>();
    }

    public void addCustomers(List<Person> newCustomers) throws TooManyCustomersInBarException {
        if (customers.size() + newCustomers.size() > numberOfPlaces) {
            throw new TooManyCustomersInBarException();
        }

        this.customers.addAll(newCustomers);
    }

    public double command(List<Person> customers, double pricePerCocktail, Person payer, int nbGlass) {
        double totalCost = pricePerCocktail * customers.size() * nbGlass;

        if (payer != null) {
            // Payer pays for everyone
            payer.setAmountToPay(payer.getAmountToPay() + totalCost);
            for (Person customer : customers) {
                customer.setGlassConsumed(customer.getGlassConsumed() + nbGlass);
            }
        } else {
            // Each customer pays for their own drinks
            for (Person customer : customers) {
                customer.setAmountToPay(customer.getAmountToPay() + pricePerCocktail * nbGlass);
                customer.setGlassConsumed(customer.getGlassConsumed() + nbGlass);
            }
        }

        return totalCost;
    }

    public int getNumberOfPlaces() {
        return this.numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public List<Person> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<Person> customers) {
        this.customers = customers;
    }


}
