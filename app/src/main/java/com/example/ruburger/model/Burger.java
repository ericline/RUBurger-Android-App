package com.example.ruburger.model;

import java.util.ArrayList;

/**
 * Represents a Burger menu item, which is a specialized type of Sandwich.
 * A Burger always uses a beef patty, and may be either single or double patty.
 * Additional toppings (add-ons) can be included and affect the total price.
 * @author Eric Lin, Anish Mande
 */
public class Burger extends Sandwich {
    private boolean doublePatty;

    /**
     * Constructs a Sandwich with specified bread, protein, and add-ons.
     * @param bread   the bread type
     * @param doublePatty if single or double patty
     * @param addOns  list of add-on toppings
     */
    public Burger(Bread bread, boolean doublePatty, ArrayList<AddOns> addOns) {
        super(bread, Protein.BEEF_PATTY, addOns);
        this.doublePatty = doublePatty;
    }

    /**
     * Calculates the total price of the burger.
     * Base price is $6.99 for a single patty, with an additional $2.50 for a double patty.
     * Each selected add-on contributes its individual price.
     * The total is multiplied by the quantity.
     * @return the total price of the burger
     */
    @Override
    public double price() {
        double basePrice = 6.99;
        if (doublePatty) {
            basePrice += 2.50;
        }

        double addOnTotal = 0.0;
        if (addOns != null) {
            for (AddOns addOn : addOns) {
                addOnTotal += addOn.getPrice();
            }
        }
        return (basePrice + addOnTotal) * quantity;
    }

    /**
     * Returns a string representation of the burger, including whether it's
     * a single or double patty, the type of bread, selected add-ons, quantity,
     * and total price.
     * @return a string describing the burger
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(doublePatty ? "Double" : "Single").append(" Patty Burger on ").append(bread);
        if (addOns != null && !addOns.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i));
                if (i < addOns.size() - 1) sb.append(", ");
            }
        }
        sb.append(" x").append(quantity);
        sb.append(" ($").append(String.format("%.2f", price())).append(")");
        return sb.toString();
    }
}
