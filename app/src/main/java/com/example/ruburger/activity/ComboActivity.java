package com.example.ruburger.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ruburger.R;
import com.example.ruburger.model.Beverage;
import com.example.ruburger.model.Burger;
import com.example.ruburger.model.Combo;
import com.example.ruburger.model.Flavor;
import com.example.ruburger.model.Order;
import com.example.ruburger.model.Sandwich;
import com.example.ruburger.model.Side;
import com.example.ruburger.model.Size;

/**
 * Activity for creating a Combo meal with a sandwich, side, and beverage.
 * Users can customize the combo and add it to their current order.
 * @author Eric Lin, Anish Mande
 */
public class ComboActivity extends AppCompatActivity {

    private Burger burger;
    private Sandwich sandwich;
    private Order currentOrder;
    private Combo combo;

    private Spinner sidesOptionSpinner, drinksOptionSpinner, quantitySpinner;
    private ImageView sidesImage, drinksImage;
    private TextView subtotalText, sandwichText;

    /**
     * Initializes the ComboActivity, sets up UI components,
     * and prepares listeners and default selections.
     *
     * @param savedInstanceState Saved instance state (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        sidesOptionSpinner = findViewById(R.id.sides_option);
        drinksOptionSpinner = findViewById(R.id.drinks_option);
        quantitySpinner = findViewById(R.id.quantity_option);
        sidesImage = findViewById(R.id.sides_image);
        drinksImage = findViewById(R.id.drinks_image);
        subtotalText = findViewById(R.id.subtotal);
        sandwichText = findViewById(R.id.burger_txt);

        currentOrder = Order.getInstance();

        Intent intent = getIntent();
        if (intent.hasExtra("BURGER")) {
            burger = (Burger) getIntent().getSerializableExtra("BURGER");
            sandwich = burger;
        } else if (intent.hasExtra("SANDWICH")) {
            sandwich = (Sandwich) getIntent().getSerializableExtra("SANDWICH");
        }

        setupSidesSpinner();
        setupDrinksSpinner();
        setupQuantitySpinner();
        setupListeners();

        loadUI();
    }

    /**
     * Sets up the sides selection spinner with available side options.
     */
    private void setupSidesSpinner() {
        ArrayAdapter<String> sidesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Chips", "Apple Slices"});
        sidesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sidesOptionSpinner.setAdapter(sidesAdapter);
        sidesOptionSpinner.setSelection(0);
    }

    /**
     * Sets up the drinks selection spinner with available drink options.
     */
    private void setupDrinksSpinner() {
        ArrayAdapter<String> drinksAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Coca Cola", "Tea", "Juice"});
        drinksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinksOptionSpinner.setAdapter(drinksAdapter);
        drinksOptionSpinner.setSelection(0);
    }

    /**
     * Sets up the quantity selection spinner with values from 1 to 10.
     */
    private void setupQuantitySpinner() {
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new Integer[]{1,2,3,4,5,6,7,8,9,10});
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);
    }

    /**
     * Sets up listeners for spinners to update images and subtotal dynamically when options are changed.
     */
    private void setupListeners() {
        sidesOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSidesImage();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        drinksOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateDrinksImage();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubtotal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * Loads the initial UI based on the passed-in sandwich or burger.
     * Sets initial quantity, images, and subtotal.
     */
    private void loadUI() {
        if (sandwich instanceof Burger) {
            burger = (Burger) sandwich;
            quantitySpinner.setSelection(burger.getQuantity() - 1);
        } else if (sandwich != null) {
            quantitySpinner.setSelection(sandwich.getQuantity() - 1);
        }
        updateSidesImage();
        updateDrinksImage();
        updateSubtotal();
    }

    /**
     * Updates the image view based on the currently selected side.
     */
    private void updateSidesImage() {
        String selected = (String) sidesOptionSpinner.getSelectedItem();
        if (selected.equals("Chips")) {
            sidesImage.setImageResource(R.drawable.chips);
        } else if (selected.equals("Apple Slices")) {
            sidesImage.setImageResource(R.drawable.apples);
        }
    }

    /**
     * Updates the image view based on the currently selected drink.
     */
    private void updateDrinksImage() {
        String selected = (String) drinksOptionSpinner.getSelectedItem();
        if (selected.equals("Coca Cola")) {
            drinksImage.setImageResource(R.drawable.cola);
        } else if (selected.equals("Tea")) {
            drinksImage.setImageResource(R.drawable.tea);
        } else if (selected.equals("Juice")) {
            drinksImage.setImageResource(R.drawable.juice);
        }
    }

    /**
     * Updates the subtotal based on the selected sandwich, side, drink, and quantity.
     * Displays an error if no sandwich was passed to the activity.
     */
    private void updateSubtotal() {
        Integer qty = (Integer) quantitySpinner.getSelectedItem();
        if (qty == null) return;

        if (sandwich instanceof Burger) {
            burger.setQuantity(qty);
            subtotalText.setText(String.format("$%.2f", burger.price() + 2.00 * qty));
        } else if (sandwich != null) {
            sandwich.setQuantity(qty);
            subtotalText.setText(String.format("$%.2f", sandwich.price() + 2.00 * qty));
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("No sandwich or burger selected. Please return to menu.")
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .show();
        }
        if (sandwich != null) {
            sandwichText.setText(sandwich.toString());
        }
    }

    /**
     * Adds the created Combo to the current order and returns the user to the main menu.
     *
     * @param view The view that triggered this action (button).
     */
    public void addToOrder(View view) {
        Beverage drink = new Beverage(Size.MEDIUM, Flavor.fromString((String) drinksOptionSpinner.getSelectedItem()));
        Side side = sidesOptionSpinner.getSelectedItem().equals("Chips") ? Side.CHIPS : Side.APPLE_SLICES;

        if (sandwich instanceof Burger) {
            combo = new Combo(burger, drink, side);
        } else {
            combo = new Combo(sandwich, drink, side);
        }

        currentOrder.addItem(combo);

        new AlertDialog.Builder(this)
                .setTitle("Order Confirmed")
                .setMessage(combo.toString() + " has been added to the order.")
                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent(ComboActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}
