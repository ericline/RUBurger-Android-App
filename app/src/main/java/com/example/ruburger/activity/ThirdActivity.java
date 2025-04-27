package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruburger.R;

/**
 * Demo the LinearLayout and Toast messages.
 * When you create an Android project, by default the root of the component tree is a ConstrainLayout.
 * You can right click on the ConstrainLayout and select Convert View to other layout.
 * @author Lily Chang
 */
public class ThirdActivity extends AppCompatActivity {
    private int count = 0;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tv = findViewById(R.id.textView);
        tv.setText(String.valueOf(count));
    }

    /**
     * This is the event handler to display a Toast message.
     * @param view The Android View that fired the event.
     */
    public void displayToast(View view) {
        //Toast.LENGTH_SHORT --> the message on the screen stays for a shorter period of time.
        Toast.makeText(this, "Hello CS213", Toast.LENGTH_LONG).show();
    }

    /**
     * A simple counter.
     * Whenever the button get clicked, the count will be increased by one and the value will be
     * updated on the screen
     * @param view the Android View that fired the event.
     */
    public void displayCount(View view) {
        count++;
        tv.setText(String.valueOf(count));
    }
}