package com.example.ruburger.model;

/**
 * This class represents a Combo meal, which includes a Sandwich, a Beverage, and a Side.
 * @author Eric Lin, Anish Mande
 */
public class Combo extends MenuItem{
    private final Sandwich sandwich;
    private final Beverage drink;
    private final Side side;

    /**
     * Constructs a Combo with a sandwich, drink, and side.
     * @param sandwich the sandwich component
     * @param drink the drink component
     * @param side the side component
     */
    public Combo(Sandwich sandwich, Beverage drink, Side side) {
        this.sandwich = sandwich;
        this.drink = drink;
        this.side = side;

        setQuantity(sandwich.getQuantity());
    }

    /**
     * Calculates the total price of the combo meal.
     * Base price = sandwich + $2 combo up-charge (includes drink and side)
     * @return the total combo price
     */
    @Override
    public double price() {
        return (sandwich.price() + (2.00 * quantity));
    }

    /**
     * Returns a string representation of the combo, including its sandwich information,
     * side and drink selection, and price before combo.
     * @return a string describing the combo
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Combo Meal: ");
        sb.append(sandwich.withoutPrice()).append(String.format(" (%.2f", price())).append(")").append("\n");
        sb.append("  + Side: ").append(side).append("\n");
        sb.append("  + Drink: ").append(drink.withoutPrice()).append("\n");
        return sb.toString();
    }
}

