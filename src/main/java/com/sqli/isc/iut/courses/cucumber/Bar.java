package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

public class Bar {

    private int numberOfPlaces;

    List<String> customers = new ArrayList<String>();

    public Bar(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public boolean addCustomers(List<String> customers) {
        if (customers.size() + customers.size() > numberOfPlaces) {
            return false;
        }

        customers.addAll(customers);
        return true;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public void setcustomers(List<String> customers) {
        this.customers = customers;
    }
}
