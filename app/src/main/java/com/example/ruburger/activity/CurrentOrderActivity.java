package com.example.ruburger.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruburger.R;
import com.example.ruburger.model.MenuItem;
import com.example.ruburger.model.Order;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView itemList;
    private TextView subtotalField, taxField, totalField, orderNumberField;

    private ArrayAdapter<MenuItem> adapter;
    private Order currentOrder;
    private static ArrayList<Order> orderArchive = new ArrayList<>();

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
        orderArchive = (ArrayList<Order>) getIntent().getSerializableExtra("ORDER_ARCHIVE");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, currentOrder.getItems());
        itemList.setAdapter(adapter);
        itemList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        updatePrices();
        orderNumberField.setText("Order #" + currentOrder.getNumber());

    }

    private void updatePrices() {
        double subtotal = currentOrder.calculateTotal();
        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        subtotalField.setText(String.format("$%.2f", subtotal));
        taxField.setText(String.format("$%.2f", tax));
        totalField.setText(String.format("$%.2f", total));

        adapter.notifyDataSetChanged();
    }

    public void sendOrder(View view) {
        if (currentOrder.getItems().isEmpty()) {
            showAlert("Empty Order", "Cannot send an empty order.", false);
            return;
        }

        orderArchive.add(currentOrder.cloneOrder());
        currentOrder.resetOrder();

        showAlert("Order Sent", orderNumberField.getText() + " has been sent.", true);
    }

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

    public void cancelEntireOrder(View view) {
        if (currentOrder.getItems().isEmpty()) {
            showAlert("Empty Order", "There are no items to cancel.", false);
            return;
        }

        currentOrder.resetOrder();

        showAlert("Order Canceled", orderNumberField.getText() + " has been canceled.", true);
    }

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
