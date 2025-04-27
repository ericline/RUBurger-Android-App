package com.example.ruburger.model;

/**
 * Enum representing the available drink flavors for a beverage.
 * Each flavor has a display-friendly name.
 * @author Eric Lin, Anish Mande
 */
public enum Flavor {
    COCA_COLA("Coca Cola"),
    DIET_COKE("Diet Coke"),
    SPRITE("Sprite"),
    ROOT_BEER("Root Beer"),
    FANTA("Fanta"),
    PEPSI("Pepsi"),
    DR_PEPPER("Dr Pepper"),
    LEMONADE("Lemonade"),
    ICED_TEA("Iced Tea"),
    MILK_SHAKE("Milk Shake"),
    WATER("Water"),
    FRUIT_PUNCH("Fruit Punch"),
    JUICE("Juice"),
    COFFEE("Coffee"),
    TEA("Tea");

    private final String name;

    /**
     * Constructs a Flavor enum constant with a given display name.
     * @param name the name of the flavor to display
     */
    Flavor(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of the flavor.
     * @return the flavor's display name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the corresponding Flavor enum constant for a given string.
     * The comparison is case-insensitive and based on the display name.
     * @param name the name to match
     * @return the matching Flavor enum constant, or null if no match is found
     */
    public static Flavor fromString(String name) {
        for (Flavor flavor : Flavor.values()) {
            if (flavor.getName().equalsIgnoreCase(name)) {
                return flavor;
            }
        }
        return null;
    }

    /**
     * Returns the string representation of the flavor,
     * used for display purposes (e.g., in UI components).
     * @return the flavor name
     */
    @Override
    public String toString() {
        return name;
    }
}
