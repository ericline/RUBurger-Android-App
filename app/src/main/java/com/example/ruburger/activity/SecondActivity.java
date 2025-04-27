package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ruburger.R;

/**
 * Demo how to retrieve the extra data encapsulated in an Intent object.
 * @author Lily Chang
 */
public class SecondActivity extends AppCompatActivity {
    private TextView tv_integer, tv_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv_integer = findViewById(R.id.tv_integer); //get the reference to the TextView object
        tv_string = findViewById(R.id.tv_string); //get the reference to the TextView object
        Intent intent = getIntent(); //get the reference to the Intent object which start this activity.
        if (intent.hasExtra("INTKEY"))
            tv_integer.setText(String.valueOf(intent.getIntExtra("INTKEY", 0)));
        if (intent.hasExtra("STRKEY"))
            tv_string.setText(intent.getStringExtra("STRKEY"));
    }
}