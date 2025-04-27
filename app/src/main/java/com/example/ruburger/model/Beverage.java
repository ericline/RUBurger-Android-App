package com.example.ruburger.model;

/**
 * This class represents a Beverage menu item with a specific flavor and size.
 * It extends the abstract MenuItem class and implements pricing logic
 * based on beverage size.
 * @author Eric Lin, Anish Mande
 */
public class Beverage extends MenuItem {
    private final Size size;
    private final Flavor flavor;

    /**
     * Constructs a Beverage with the given size and flavor.
     * @param size the size of the beverage
     * @param flavor the flavor of the beverage
     */
    public Beverage(Size size, Flavor flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    /**
     * Calculates the price of the beverage based on its size and quantity.
     * @return the total price of the beverage
     */
    @Override
    public double price() {
        if (size.equals(Size.SMALL)) {
            return 1.99 * quantity;
        }
        else if(size.equals(Size.MEDIUM)) {
            return 2.49 * quantity;
        }
        else if(size.equals(Size.LARGE)) {
            return 2.99 * quantity;
        }
        else {
            return 0;
        }
    }

    /**
     * Returns a string representation of the beverage without price or quantity.
     * @return a simple description of the beverage
     */
    public String withoutPrice() {
        return size + " " + flavor;
    }

    /**
     * Returns a formatted string representation of the beverage,
     * including its size, flavor, quantity, and total price.
     * @return a string describing the beverage
     */
    @Override
    public String toString() {
        return size + " " + flavor + " x" + quantity + " ($" + String.format("%.2f", price()) + ")";
    }
}

