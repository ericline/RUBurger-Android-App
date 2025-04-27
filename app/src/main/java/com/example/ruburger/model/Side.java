package com.example.ruburger.model;

/**
 * Enum representing the available side items on the menu.
 * Each side has a display name and a fixed base price (not including size/quantity modifiers).
 * @author Eric Lin, Anish Mande
 */
public enum Side {
    CHIPS("Chips", 1.99),
    FRIES("Fries", 2.49),
    ONION_RINGS("Onion Rings", 3.29),
    APPLE_SLICES("Apple Slices", 1.29);

    private final String name;
    private final double price;

    /**
     * Constructs a Side enum constant with a display name and price.
     * @param name  the name of the side item to be displayed
     * @param price the base price of the side item
     */
    Side(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the display name of the side item.
     * @return the name of the side
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the base price of the side item.
     * @return the price of the side
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the string representation of the side item,
     * typically used for display in UI components.
     * @return the name of the side
     */
    @Override
    public String toString() {
        return name;
    }
}
