package com.example.ruburger.model;

/**
 * Enum representing available sizes for menu items such as beverages and sides.
 * Each size has a display-friendly name.
 * @author Eric Lin, Anish Mande
 */
public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String name;

    /**
     * Constructs a Size enum constant with a display name.
     * @param name the name of the size
     */
    Size(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of the size.
     * @return the name of the size
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the string representation of the size, typically used in UI.
     * @return the size name
     */
    @Override
    public String toString() {
        return name;
    }
}
