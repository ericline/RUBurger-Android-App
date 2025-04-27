package com.example.ruburger.model;

/**
 * Enum representing the available types of protein for a sandwich or burger.
 * @author Eric Lin, Anish Mande
 */
public enum Protein {
    ROAST_BEEF("Roast Beef"),
    SALMON("Salmon"),
    CHICKEN("Chicken"),
    BEEF_PATTY("Beef Patty");

    private final String name;

    /**
     * Constructs a Protein enum constant with the given display name.
     * @param name the display name of the protein
     */
    Protein(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of the protein.
     * @return the name of the protein
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the string representation of the protein,
     * @return the name of the protein
     */
    @Override
    public String toString() {
        return name;
    }
}
