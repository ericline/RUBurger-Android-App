package com.example.ruburger.model;

/**
 * Enum representing available add-ons for a sandwich.
 * Each add-on has a display name and a corresponding price.
 * @author Eric Lin, Anish Mande
 */
public enum AddOns {

    LETTUCE("Lettuce", 0.30),
    TOMATOES("Tomatoes", 0.30),
    ONIONS("Onions", 0.30),
    AVOCADO("Avocado", 0.50),
    CHEESE("Cheese", 1.00);

    private final String name;
    private final double price;

    /**
     * Constructor for the AddOns enum.
     * @param name  the display name of the add-on
     * @param price the additional price of the add-on
     */
    AddOns(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the display name of the add-on.
     * @return the name of the add-on
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the add-on.
     * @return the price of the add-on
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the string representation of the add-on, used for display purposes.
     * @return the name of the add-on
     */
    @Override
    public String toString() {
        return name;
    }
}
