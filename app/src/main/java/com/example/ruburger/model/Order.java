package com.example.ruburger.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a customer's order containing multiple menu items.
 * Each order has a unique order number and maintains a list of MenuItems.
 * This class uses a singleton pattern for creating a single active order instance at a time.
 * @author Eric Lin, Anish Mande
 */
public class Order implements Serializable {
    private int number; //a unique integer to identify the order
    private ArrayList<MenuItem> items;
    private static int nextNumber = 1;
    private static Order instance;

    /**
     * Private constructor for a new order with an auto-generated unique order number.
     */
    private Order() {
        this.number = nextNumber++;
        this.items = new ArrayList<>();
    }

    /**
     * Private constructor for cloning or restoring an order with a specific number and items.
     * @param number the specific order number
     * @param items  the list of menu items in the order
     */
    private Order(int number, ArrayList<MenuItem> items) {
        this.number = number;
        this.items = new ArrayList<>(items);
    }

    /**
     * Returns the singleton instance of the current active order.
     * If no active order exists, creates a new one.
     * @return the current {@code Order} instance
     */
    public static synchronized Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    /**
     * Creates a shallow clone of the current order, including the same number and items list.
     * @return a new Order instance with copied data
     */
    public Order cloneOrder() {
        return new Order(this.number, this.items);
    }

    /**
     * Adds a new MenuItem to the order.
     * @param item the item to be added
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Removes a MenuItem from the order.
     *
     * @param item the item to be removed
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    /**
     * Calculates the subtotal (before tax) for the order.
     * @return the total price of all items in the order
     */
    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : items) {
            total += item.price();
        }
        return total;
    }

    /**
     * Returns the unique number of this order.
     * @return the order number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the list of items in the order.
     * @return an ArrayList of MenuItems
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Resets the current singleton order instance by creating a new order.
     * As a result, increments unique order number by one as to not reuse IDs.
     */
    public void resetOrder() {
        instance = new Order();
    }

    /**
     * Returns a string representation of the order, including item descriptions,
     * subtotal, tax, and total price.
     * @return a formatted string summarizing the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(number).append("\n");
        sb.append("----------------------------\n");

        if (items.isEmpty()) {
            sb.append("No items in this order.\n");
        } else {
            for (MenuItem item : items) {
                sb.append(item.toString()).append("\n");
            }
        }

        double tax = 0.06625;
        double subtotal = calculateTotal();

        sb.append("----------------------------\n");
        sb.append(String.format("Subtotal: $%.2f\n", subtotal));
        sb.append(String.format("Tax (6.625%%): $%.2f\n", subtotal * tax));
        sb.append(String.format("Total: $%.2f\n", subtotal * (1 + tax)));

        return sb.toString();
    }
}
