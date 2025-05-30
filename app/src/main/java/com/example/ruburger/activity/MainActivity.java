package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ruburger.R;

/**
 * Main menu activity for the RU Burger app.
 * Provides navigation to different parts of the app, such as Burger menu, Sides, Beverages,
 * Current Orders, and more.
 * @author Eric Lin, Anish Mande
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     * Initializes the main layout.
     *
     * @param savedInstanceState The saved instance state bundle (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Navigates to the BurgerActivity where users can customize burgers.
     *
     * @param view The button view that triggered this method.
     */
    public void goToBurger(View view) {
        Intent intent = new Intent(this, BurgerActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the SandwichActivity where users can customize sandwiches.
     *
     * @param view The button view that triggered this method.
     */
    public void goToSandwich(View view) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the BeveragesActivity where users can select beverages.
     *
     * @param view The button view that triggered this method.
     */
    public void goToBeverages(View view) {
        Intent intent = new Intent(this, BeveragesActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the SidesActivity where users can select side items.
     *
     * @param view The button view that triggered this method.
     */
    public void goToSides(View view) {
        Intent intent = new Intent(this, SidesActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the CurrentOrderActivity where users can view and modify their current order.
     *
     * @param view The button view that triggered this method.
     */
    public void goToCurrentOrder(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the AllOrdersActivity where users can review previous orders.
     *
     * @param view The button view that triggered this method.
     */
    public void goToAllOrders(View view) {
        Intent intent = new Intent(this, AllOrdersActivity.class);
        startActivity(intent);
    }

}