package com.example.gamepowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewTopTime;
    private Button buttonLevel1, buttonLevel2, buttonLevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getData();
    }

    private void initUI() {
        textViewTopTime = findViewById(R.id.textViewTopTime);
        buttonLevel1 = findViewById(R.id.buttonLevel1);
        buttonLevel2 = findViewById(R.id.buttonLevel2);
        buttonLevel3 = findViewById(R.id.buttonLevel3);
    }

    private void initListeners() {
        buttonLevel1.setOnClickListener(this);
        buttonLevel2.setOnClickListener(this);
        buttonLevel3.setOnClickListener(this);
    }

    private void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        textViewTopTime.setText(String.valueOf(sharedPreferences.getInt("numTimer", 0)));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        if (v.getId() == R.id.buttonLevel1) {
            intent.putExtra("level", 1);
            startActivity(intent);
        }
        if (v.getId() == R.id.buttonLevel2) {
            intent.putExtra("level", 2);
            startActivity(intent);
        }
        if (v.getId() == R.id.buttonLevel3) {
            intent.putExtra("level", 3);
            startActivity(intent);
        }
    }

}
