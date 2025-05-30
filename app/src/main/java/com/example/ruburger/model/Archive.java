package com.example.ruburger.model;

import java.util.ArrayList;

/**
 * A singleton class that maintains an archive of all completed orders.
 * Provides methods to retrieve, add to, or clear the order archive.
 * @author Eric Lin
 */
public class Archive {
    private static final ArrayList<Order> orderArchive = new ArrayList<>();

    /**
     * Retrieves the list of archived orders.
     *
     * @return The list of all archived orders.
     */
    public static ArrayList<Order> getOrderArchive() {
        return orderArchive;
    }

    /**
     * Adds a new order to the archive.
     *
     * @param order The order to be added to the archive.
     */
    public static void addOrder(Order order) {
        orderArchive.add(order);
    }
}
