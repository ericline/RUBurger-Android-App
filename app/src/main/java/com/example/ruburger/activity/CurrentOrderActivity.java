package com.example.ruburger.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruburger.R;
import com.example.ruburger.model.Archive;
import com.example.ruburger.model.MenuItem;
import com.example.ruburger.model.Order;

/**
 * Activity for viewing, modifying, and finalizing the user's current order.
 * Displays items in the order, allows item removal, sending, or canceling the order.
 * @author Eric Lin, Anish Mande
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private ListView itemList;
    private TextView subtotalField, taxField, totalField, orderNumberField;

    private ArrayAdapter<MenuItem> adapter;
    private Order currentOrder;

    /**
     * Initializes the activity, loads UI components, and populates the current order's items.
     *
     * @param savedInstanceState Saved instance state (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        itemList = findViewById(R.id.item_list);
        subtotalField = findViewById(R.id.subtotal_field);
        taxField = findViewById(R.id.tax_field);
        totalField = findViewById(R.id.total_field);
        orderNumberField = findViewById(R.id.order_number_txt);

        currentOrder = Order.getInstance();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, currentOrder.getItems());
        itemList.setAdapter(adapter);
        itemList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        updatePrices();
        orderNumberField.setText("Order #" + currentOrder.getNumber());

    }

    /**
     * Updates and displays the subtotal, tax, and total based on current order contents.
     */
    private void updatePrices() {
        double subtotal = currentOrder.calculateTotal();
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        subtotalField.setText(String.format("$%.2f", subtotal));
        taxField.setText(String.format("$%.2f", tax));
        totalField.setText(String.format("$%.2f", total));

        adapter.notifyDataSetChanged();
    }

    /**
     * Sends the current order to the archive if it is not empty, resets the current order,
     * and displays a confirmation alert.
     *
     * @param view The view triggering this action (button).
     */
    public void sendOrder(View view) {
        if (currentOrder.getItems().isEmpty()) {
            showAlert("Empty Order", "Cannot send an empty order.", false);
            return;
        }

        Archive.addOrder(currentOrder.cloneOrder());
        currentOrder.resetOrder();

        showAlert("Order Sent", orderNumberField.getText() + " has been sent.", true);
    }

    /**
     * Removes the selected item from the current order if an item is selected.
     *
     * @param view The view triggering this action (button).
     */
    public void removeSelectedItem(View view) {
        int position = itemList.getCheckedItemPosition();
        if (position == ListView.INVALID_POSITION) {
            showAlert("No Selection", "Please select an item to remove.", false);
            return;
        }

        MenuItem selectedItem = adapter.getItem(position);
        if (selectedItem != null) {
            currentOrder.removeItem(selectedItem);
            itemList.clearChoices();
            adapter.notifyDataSetChanged();
            updatePrices();
        }
    }

    /**
     * Cancels the entire current order if it is not empty and displays a confirmation alert.
     *
     * @param view The view triggering this action (button).
     */
    public void cancelEntireOrder(View view) {
        if (currentOrder.getItems().isEmpty()) {
            showAlert("Empty Order", "There are no items to cancel.", false);
            return;
        }

        currentOrder.resetOrder();

        showAlert("Order Canceled", orderNumberField.getText() + " has been canceled.", true);
    }

    /**
     * Displays an alert dialog with a custom title and message.
     * Optionally finishes the activity after acknowledging the alert.
     *
     * @param title        The title of the alert.
     * @param message      The message body of the alert.
     * @param finishAfter  Whether the activity should close after the alert is acknowledged.
     */
    private void showAlert(String title, String message, boolean finishAfter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                    if (finishAfter) {
                        finish();
                    } else {
                        updatePrices();
                    }
                });
        builder.show();
    }
}
