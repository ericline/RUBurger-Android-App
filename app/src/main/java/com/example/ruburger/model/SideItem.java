package com.example.ruburger.model;

/**
 * Represents a specific side item in an order, including its size and quantity.
 * Extends MenuItem and includes logic for price calculation based on size.
 * @author Eric Lin, Anish Mande
 */
public class SideItem extends MenuItem {
    private final Side side;
    private final Size size;

    /**
     * Constructs a new SideItem with a given side type and size.
     * @param side the type of side (e.g., Fries, Onion Rings)
     * @param size the size of the side (e.g., Small, Medium, Large)
     */
    public SideItem(Side side, Size size) {
        this.side = side;
        this.size = size;
    }

    /**
     * Calculates the total price of the side item.
     * The base price is determined by the side type and increased based on size.
     * - Small is the base price.
     * - Medium adds $0.50
     * - Large adds $1.00
     * The total is then multiplied by the quantity.
     * @return the total price of this side item
     */
    @Override
    public double price() {
        double basePrice = side.getPrice();
        if (size.equals(Size.LARGE)) {
            basePrice += 1.00;
        }
        else if (size.equals(Size.MEDIUM)) {
            basePrice += 0.50;
        }
        return basePrice *= quantity;
    }

    /**
     * Returns a string representation of the side item, including size, name,
     * quantity, and formatted total price.
     * @return a string describing the side item
     */
    @Override
    public String toString() {
        return size + " " + side.getName() + " x" + quantity + " ($" + String.format("%.2f", price()) + ")";
    }
}
