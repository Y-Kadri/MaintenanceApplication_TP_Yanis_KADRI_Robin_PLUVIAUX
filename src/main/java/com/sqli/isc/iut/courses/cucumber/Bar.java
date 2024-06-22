package com.sqli.isc.iut.courses.cucumber;

import java.util.List;

import com.sqli.isc.iut.courses.exceptions.TooManyCustomersInBarException;

public class Bar {

    private int numberOfPlaces;
    private int numberOfCustomers;

    public Bar(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfCustomers = 0;
    }

    public void addCustomers(List<String> customers) throws TooManyCustomersInBarException {
        if (numberOfCustomers + customers.size() > numberOfPlaces) {
            throw new TooManyCustomersInBarException();
        }

        for (String customer: customers) {
            numberOfCustomers++;
        }
        
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }
}
