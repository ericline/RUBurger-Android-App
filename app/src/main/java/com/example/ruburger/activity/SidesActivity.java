package com.example.ruburger.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruburger.R;
import com.example.ruburger.model.Order;
import com.example.ruburger.model.Side;
import com.example.ruburger.model.SideItem;
import com.example.ruburger.model.Size;

public class SidesActivity extends AppCompatActivity {

    private Spinner sidesOption;
    private Spinner sizeOption;
    private Spinner quantityOption;
    private TextView subtotalText;

    private Order currentOrder;
    private SideItem sideItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides);

        sidesOption = findViewById(R.id.sides_option);
        sizeOption = findViewById(R.id.drinks_option);
        quantityOption = findViewById(R.id.quantity_option);
        subtotalText = findViewById(R.id.subtotal);

        currentOrder = Order.getInstance();

        setupSidesOption();
        setupSizeOption();
        setupQuantityOption();

        updateSubtotal();
    }

    private void setupSidesOption() {
        ArrayAdapter<Side> sidesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Side.values()
        );
        sidesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sidesOption.setAdapter(sidesAdapter);
        sidesOption.setSelection(0);

        sidesOption.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void setupSizeOption() {
        ArrayAdapter<Size> sizeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Size.values()
        );
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeOption.setAdapter(sizeAdapter);
        sizeOption.setSelection(1); // Medium

        sizeOption.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void setupQuantityOption() {
        Integer[] quantities = {1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, quantities
        );
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityOption.setAdapter(quantityAdapter);
        quantityOption.setSelection(0);

        quantityOption.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void updateSubtotal() {
        Side selectedSide = (Side) sidesOption.getSelectedItem();
        Size selectedSize = (Size) sizeOption.getSelectedItem();
        Integer quantity = (Integer) quantityOption.getSelectedItem();

        sideItem = new SideItem(selectedSide, selectedSize);
        sideItem.setQuantity(quantity);

        double price = sideItem.price();
        subtotalText.setText(String.format("$%.2f", price));
    }

    public void addToOrder(View view) {
        currentOrder.addItem(sideItem);

        new AlertDialog.Builder(this)
                .setTitle("Order Confirmed")
                .setMessage(sideItem + " has been added to the order.")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
    }
}