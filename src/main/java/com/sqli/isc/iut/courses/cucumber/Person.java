package com.sqli.isc.iut.courses.cucumber;

public class Person {

    private String name;
    private double money;  
    private int maxDrinks;
    private double amountToPay;
    private int glassConsumed;

    public Person(String name, double money, int maxDrinks) {
        this.name = name;
        this.money = money;
        this.maxDrinks = maxDrinks;
        this.amountToPay = 0.0;
        this.glassConsumed = 0;
    }

    public void PayingTheBill() {
        this.money -= this.amountToPay;
        this.amountToPay = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getMaxDrinks() {
        return maxDrinks;
    }

    public void setMaxDrinks(int maxDrinks) {
        this.maxDrinks = maxDrinks;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public int getGlassConsumed() {
        return glassConsumed;
    }

    public void setGlassConsumed(int glassConsumed) {
        this.glassConsumed = glassConsumed;
    }
}
