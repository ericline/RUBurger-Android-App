package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ruburger.R;

/**
 * Demo navigation between 2 activities.
 * @author Lily Chang
 */
public class MainActivity extends AppCompatActivity {
    private Button burgerButton;
    private Button sandwichButton;
    private Button beverageButton;
    private Button sidesButton;
    private Button cartButton;
    private Button ordersButton;
    private Button exitButton;

    private int NUMBER = 20;
    private String STRING = "hi";


    /**
     * Must implement this callback method to perform any initial set up for the app.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    private void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * A callback method executed right after onCreate().
     */
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    /**
     * A callback method executed right after onStart().
     */
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    public void goToBurger(View view) {
        Intent intent = new Intent(this, BurgerActivity.class);
        startActivity(intent);
    }

    public void goToSandwich(View view) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }

    public void goToCombo(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * The event handler for the button click.
     * Add extra data to an Intent object and pass the extra data to the Activity being started.
     * INTKEY is the name to be used to retrieve the extra data NUMBER.
     * @param view the Android View which fired the event.
     */
    public void showInteger(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("INTKEY", NUMBER); //the extra data is an integer
        startActivity(intent);
    }

    /**
     * The event handler for the button click.
     * Add extra data to an Intent object and pass the extra data to the Activity being started.
     * STRKEY is the name to be used to retrieve the extra data STRING.
     * @param view the Android View which fired the event.
     */
    public void showString(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("STRKEY", STRING); //the extra data is a string
        startActivity(intent);
    }

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
    public void showLinearLayout(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

}