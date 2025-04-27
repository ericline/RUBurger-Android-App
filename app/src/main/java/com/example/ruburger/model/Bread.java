package com.example.ruburger.model;

/**
 * Enum representing the types of bread available for a sandwich.
 * Each bread type has a display-friendly name.
 * @author Eric Lin, Anish Mande
 */
public enum Bread {
    BRIOCHE("Brioche"),
    WHEAT_BREAD("Wheat Bread"),
    PRETZEL("Pretzel"),
    BAGEL("Bagel"),
    SOURDOUGH("Sourdough");

    private final String name;

    /**
     * Constructs a Bread enum value with a given display name.
     * @param name the name of the bread to display
     */
    Bread(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of the bread.
     * @return the name of the bread
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the string representation of the bread.
     * @return the name of the bread
     */
    @Override
    public String toString() {
        return name;
    }
}
