package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruburger.R;
import com.example.ruburger.model.AddOns;
import com.example.ruburger.model.Bread;
import com.example.ruburger.model.Order;
import com.example.ruburger.model.Burger;

import java.util.ArrayList;

public class BurgerActivity extends AppCompatActivity{

    private Button addOrderButton, comboButton;
    private RadioButton singleOption, doubleOption;
    private RadioButton briocheOption, wheatOption, pretzelOption;
    private CheckBox lettuceOption, tomatoOption, onionOption, avocadoOption, cheeseOption;
    private Spinner quantitySpinner;
    private TextView subtotalText;
    private Order currentOrder;
    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        currentOrder = Order.getInstance();

        addOrderButton = findViewById(R.id.add_order_bt);
        comboButton = findViewById(R.id.combo_bt);
        singleOption = findViewById(R.id.single_option);
        doubleOption = findViewById(R.id.double_option);
        briocheOption = findViewById(R.id.brioche_option);
        wheatOption = findViewById(R.id.wheat_option);
        pretzelOption = findViewById(R.id.pretzel_option);
        lettuceOption = findViewById(R.id.lettuce_option);
        tomatoOption = findViewById(R.id.tomato_option);
        onionOption = findViewById(R.id.onion_option);
        avocadoOption = findViewById(R.id.avocado_option);
        cheeseOption = findViewById(R.id.cheese_option);
        quantitySpinner = findViewById(R.id.quantity_option);
        subtotalText = findViewById(R.id.subtotal);

        setupQuantitySpinner();
        setupListeners();
        updateSubtotal();

    }

    private void setupQuantitySpinner() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new Integer[]{1,2,3,4,5,6,7,8,9,10});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setSelection(0);

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void setupListeners() {
        singleOption.setOnClickListener(v -> updateSubtotal());
        doubleOption.setOnClickListener(v -> updateSubtotal());

        briocheOption.setOnClickListener(v -> updateSubtotal());
        wheatOption.setOnClickListener(v -> updateSubtotal());
        pretzelOption.setOnClickListener(v -> updateSubtotal());

        lettuceOption.setOnClickListener(v -> updateSubtotal());
        tomatoOption.setOnClickListener(v -> updateSubtotal());
        onionOption.setOnClickListener(v -> updateSubtotal());
        avocadoOption.setOnClickListener(v -> updateSubtotal());
        cheeseOption.setOnClickListener(v -> updateSubtotal());

    }

    private void updateSubtotal() {
        boolean isDouble = doubleOption.isChecked();

        // Determine bread
        Bread bread = null;
        if (briocheOption.isChecked()) {
            bread = Bread.BRIOCHE;
        } else if (wheatOption.isChecked()) {
            bread = Bread.WHEAT_BREAD;
        } else if (pretzelOption.isChecked()) {
            bread = Bread.PRETZEL;
        }

        // Gather add-ons
        ArrayList<AddOns> selectedAddOns = new ArrayList<>();
        if (lettuceOption.isChecked()) selectedAddOns.add(AddOns.LETTUCE);
        if (tomatoOption.isChecked()) selectedAddOns.add(AddOns.TOMATOES);
        if (onionOption.isChecked()) selectedAddOns.add(AddOns.ONIONS);
        if (avocadoOption.isChecked()) selectedAddOns.add(AddOns.AVOCADO);
        if (cheeseOption.isChecked()) selectedAddOns.add(AddOns.CHEESE);

        burger = new Burger(bread, isDouble, selectedAddOns);

        Integer qty = (Integer) quantitySpinner.getSelectedItem();
        if (qty != null) {
            burger.setQuantity(qty);
        }

        double price = burger.price();
        subtotalText.setText(String.format("$%.2f", price));
    }

    public void addToOrder(View view) {
        currentOrder.addItem(burger);

        new AlertDialog.Builder(this)
                .setTitle("Order Confirmed")
                .setMessage(burger.toString() + " has been added to the order.")
                .setPositiveButton("OK", (dialog, which) -> finish()) // maybe return to main menu
                .show();

        System.out.println(currentOrder);
    }

    public void makeCombo(View view) {
        Toast.makeText(this, "Combo functionality not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
