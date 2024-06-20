package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

public class Bar {

    private int numberOfPlaces;

    List<String> customersInTheBar = new ArrayList<String>();

    public Bar(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void addCustomers(List<String> customers) {
        if (customersInTheBar.size() + customersInTheBar.size() > numberOfPlaces) {
            return;
        }

        customersInTheBar.addAll(customers);
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public List<String> getCustomersInTheBar() {
        return customersInTheBar;
    }

    public void setCustomersInTheBar(List<String> customersInTheBar) {
        this.customersInTheBar = customersInTheBar;
    }
}
