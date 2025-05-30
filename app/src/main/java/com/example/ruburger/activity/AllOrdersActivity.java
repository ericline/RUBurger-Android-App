package com.example.ruburger.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruburger.R;
import com.example.ruburger.model.Archive;
import com.example.ruburger.model.MenuItem;
import com.example.ruburger.model.Order;

import java.util.ArrayList;

/**
 * Activity for displaying and managing all archived orders.
 * Users can select an order to view its details or cancel an order.
 * @author Eric Lin, Anish Mande
 */
public class AllOrdersActivity extends AppCompatActivity {

    private Spinner orderSelector;
    private ListView orderList;
    private TextView subtotalField, taxField, totalField;

    private ArrayList<Order> orderArchive;
    private ArrayAdapter<MenuItem> orderAdapter;
    private ArrayAdapter<Integer> orderNumberAdapter;

    private Order currentOrder;

    /**
     * Initializes the activity, loads orders from the archive,
     * and displays the first available order if present.
     *
     * @param savedInstanceState Previously saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        orderSelector = findViewById(R.id.order_selector);
        orderList = findViewById(R.id.order_list);
        subtotalField = findViewById(R.id.subtotal_field);
        taxField = findViewById(R.id.tax_field);
        totalField = findViewById(R.id.total_field);

        orderArchive = Archive.getOrderArchive();

        setupOrderSelector();

        if (!orderArchive.isEmpty()) {
            currentOrder = orderArchive.get(0);
            displayCurrentOrder();
        }
    }

    /**
     * Sets up the order selector spinner with available order numbers
     * and handles selection events to display the selected order.
     */
    private void setupOrderSelector() {
        ArrayList<Integer> orderNumbers = new ArrayList<>();
        for (Order order : orderArchive) {
            orderNumbers.add(order.getNumber());
        }
        orderNumberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        orderNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSelector.setAdapter(orderNumberAdapter);

        orderSelector.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                if (!orderArchive.isEmpty() && position >= 0 && position < orderArchive.size()) {
                    currentOrder = orderArchive.get(position);
                    displayCurrentOrder();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
    }

    /**
     * Displays the current order's items in the list view and updates the subtotal, tax, and total fields.
     */
    private void displayCurrentOrder() {
        if (currentOrder == null) return;

        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentOrder.getItems());
        orderList.setAdapter(orderAdapter);

        double subtotal = currentOrder.calculateTotal();
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        subtotalField.setText(String.format("$%.2f", subtotal));
        taxField.setText(String.format("$%.2f", tax));
        totalField.setText(String.format("$%.2f", total));
    }

    /**
     * Cancels the currently selected order.
     * Displays an alert if no items are present.
     * Otherwise removes the order and refreshes the view.
     *
     * @param view The button view that triggers this action.
     */
    public void cancelEntireOrder(View view) {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            showAlert("Empty Order", "There are no items in the order.");
            return;
        }

        orderArchive.remove(currentOrder);

        showAlert("Order Canceled", "Order has been canceled.");

        if (!orderArchive.isEmpty()) {
            currentOrder = orderArchive.get(0);
            refreshOrderSelector();
            displayCurrentOrder();
        } else {
            finish();
        }
    }

    /**
     * Refreshes the spinner (order selector) after an order is canceled.
     * Updates the list of order numbers and reselects the current order.
     */
    private void refreshOrderSelector() {
        ArrayList<Integer> orderNumbers = new ArrayList<>();
        for (Order order : orderArchive) {
            orderNumbers.add(order.getNumber());
        }
        orderNumberAdapter.clear();
        orderNumberAdapter.addAll(orderNumbers);
        orderNumberAdapter.notifyDataSetChanged();

        int idx = orderNumbers.indexOf(currentOrder.getNumber());
        if (idx >= 0) {
            orderSelector.setSelection(idx);
        }
    }

    /**
     * Displays an alert dialog with the given title and message.
     *
     * @param title   The title of the alert dialog.
     * @param message The message to be shown in the alert dialog.
     */
    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                });
        builder.show();
    }
}