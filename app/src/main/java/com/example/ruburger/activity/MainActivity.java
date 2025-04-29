package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ruburger.R;
import com.example.ruburger.model.Order;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final ArrayList<Order> orderArchive = new ArrayList<>();


    /**
     * Must implement this callback method to perform any initial set up for the app.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void goToBurger(View view) {
        Intent intent = new Intent(this, BurgerActivity.class);
        startActivity(intent);
    }

    public void goToSandwich(View view) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }

    public void goToBeverages(View view) {
        Intent intent = new Intent(this, BeveragesActivity.class);
        startActivity(intent);
    }

    public void goToSides(View view) {
        Intent intent = new Intent(this, SidesActivity.class);
        startActivity(intent);
    }

    public void goToCurrentOrder(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        intent.putExtra("ORDER_ARCHIVE", orderArchive);
        startActivity(intent);
    }

    public void goToAllOrders(View view) {
        Intent intent = new Intent(this, AllOrdersActivity.class);
        intent.putExtra("ORDER_ARCHIVE", orderArchive);
        startActivity(intent);
    }


    /**
     * The event handler for the button click.
     * Add extra data to an Intent object and pass the extra data to the Activity being started.
     * INTKEY is the name to be used to retrieve the extra data NUMBER.
     * @param view the Android View which fired the event.
     */

    /**
     * The event handler for the button click.
     * Add extra data to an Intent object and pass the extra data to the Activity being started.
     * STRKEY is the name to be used to retrieve the extra data STRING.
     * @param view the Android View which fired the event.
     */

    /**
     * Start an Activity containing a webpage. This will run the browser in order to display the
     * webpage.
     * Intent.ACTION_VIEW is an action that can be handled by the browser.
     * @param view the Android View which fired the event.
     */
    public void goRutgers(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rutgers.edu"));
        startActivity(intent);
    }

    /**
     * Demo the LinearLayout.
     * @param view the Android View which fired the event.
     */


}