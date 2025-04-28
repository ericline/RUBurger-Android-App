package com.example.ruburger.model;

import java.io.Serializable;

/**
 * Abstract base class representing a menu item in the ordering system.
 * All menu items (e.g., sandwiches, beverages, sides) should extend this class
 * and implement the price method to define their pricing logic.
 * @author Eric Lin, Anish Mande
 */
public abstract class MenuItem implements Serializable {
    protected int quantity;

    /**
     * Abstract method that must be implemented by subclasses to calculate
     * the total price based on the item’s configuration and quantity.
     * @return the total price of the item
     */
    public abstract double price();

    /**
     * Constructs a MenuItem with a default quantity of 1.
     */
    protected MenuItem() {
        this.quantity = 1;
    }

    /**
     * Sets the quantity for the menu item.
     * @param i the number of items
     */
    public void setQuantity(int i) {
        quantity = i;
    }

    /**
     * Returns the quantity of the menu item.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }
}