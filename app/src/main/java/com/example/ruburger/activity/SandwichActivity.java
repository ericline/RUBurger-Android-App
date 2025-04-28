package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruburger.R;
import com.example.ruburger.model.AddOns;
import com.example.ruburger.model.Bread;
import com.example.ruburger.model.Protein;
import com.example.ruburger.model.Order;
import com.example.ruburger.model.Sandwich;

import java.util.ArrayList;
public class SandwichActivity extends AppCompatActivity {
    private RadioButton roastBeefOption, salmonOption, chickenOption;
    private RadioButton briocheOption, wheatOption, pretzelOption;
    private CheckBox lettuceOption, tomatoOption, onionOption, avocadoOption, cheeseOption;
    private Spinner quantitySpinner;
    private TextView subtotalText;
    private Order currentOrder;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        currentOrder = Order.getInstance();

        roastBeefOption = findViewById(R.id.roast_beef);
        salmonOption = findViewById(R.id.salmon);
        chickenOption = findViewById(R.id.chicken);
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
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new Integer[]{1,2,3,4,5,6,7,8,9,10});
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);
        quantitySpinner.setSelection(0);
    }

    private void setupListeners() {
        roastBeefOption.setOnClickListener(v -> updateSubtotal());
        salmonOption.setOnClickListener(v -> updateSubtotal());
        chickenOption.setOnClickListener(v -> updateSubtotal());

        briocheOption.setOnClickListener(v -> updateSubtotal());
        wheatOption.setOnClickListener(v -> updateSubtotal());
        pretzelOption.setOnClickListener(v -> updateSubtotal());

        lettuceOption.setOnClickListener(v -> updateSubtotal());
        tomatoOption.setOnClickListener(v -> updateSubtotal());
        onionOption.setOnClickListener(v -> updateSubtotal());
        avocadoOption.setOnClickListener(v -> updateSubtotal());
        cheeseOption.setOnClickListener(v -> updateSubtotal());

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void updateSubtotal() {
        Protein protein = null;
        if (roastBeefOption.isChecked()) {
            protein = Protein.ROAST_BEEF;
        } else if (salmonOption.isChecked()) {
            protein = Protein.SALMON;
        } else if (chickenOption.isChecked()) {
            protein = Protein.CHICKEN;
        }

        Bread bread = null;
        if (briocheOption.isChecked()) {
            bread = Bread.BRIOCHE;
        } else if (wheatOption.isChecked()) {
            bread = Bread.WHEAT_BREAD;
        } else if (pretzelOption.isChecked()) {
            bread = Bread.PRETZEL;
        }

        ArrayList<AddOns> selectedAddOns = new ArrayList<>();
        if (lettuceOption.isChecked()) selectedAddOns.add(AddOns.LETTUCE);
        if (tomatoOption.isChecked()) selectedAddOns.add(AddOns.TOMATOES);
        if (onionOption.isChecked()) selectedAddOns.add(AddOns.ONIONS);
        if (avocadoOption.isChecked()) selectedAddOns.add(AddOns.AVOCADO);
        if (cheeseOption.isChecked()) selectedAddOns.add(AddOns.CHEESE);

        sandwich = new Sandwich(bread, protein, selectedAddOns);

        Integer qty = (Integer) quantitySpinner.getSelectedItem();
        if (qty != null) {
            sandwich.setQuantity(qty);
        }

        double price = sandwich.price();
        subtotalText.setText(String.format("$%.2f", price));
    }

    public void addToOrder(View view) {
        currentOrder.addItem(sandwich);

        new AlertDialog.Builder(this)
                .setTitle("Order Confirmed")
                .setMessage(sandwich.toString() + " has been added to the order.")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
    }

    public void makeCombo(View view) {
        Intent intent = new Intent(this, ComboActivity.class);
        intent.putExtra("SANDWICH", sandwich);
        startActivity(intent);
    }
}
