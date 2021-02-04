package com.example.gamepowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonNumber;
    private EditText editTextNumber;
    private TextView textViewNumber, textViewTimer;
    private int random1, counterResult = 0, numData, numDataLevel, numTimer, startNumTimer = 30000;
    private boolean stopRandom = false;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initUI();
        initListeners();
    }

    private void initUI() {
        buttonNumber = findViewById(R.id.buttonNumber);
        editTextNumber = findViewById(R.id.editTextNumber);
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewTimer = findViewById(R.id.textViewTimer);

        numData = getIntent().getIntExtra("level", 0);

        randomNumbers();
        textNumber();
        startCountDownTimer();
    }

    private void initListeners() {
        buttonNumber.setOnClickListener(this);
    }

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(startNumTimer, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                numTimer = (int) (millisUntilFinished / 1000);

                textViewTimer.setText("Seconds remaining: " + numTimer);
            }

            @Override
            public void onFinish() {
                saveTime(numTimer);

                textViewTimer.setText("");
            }
        }.start();
    }

    private void saveTime(int numTimer) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("numTimer", startNumTimer / 1000 - numTimer);
        editor.apply();

        onBackPressed();
    }

    private void randomNumbers() {
        if (!stopRandom) {
            numDataLevel = numData;
            random1 = new Random().nextInt(numDataLevel *= 10) + 1;
            stopRandom = true;
        }
    }

    private void textNumber() {
        textViewNumber.setText(random1 + " ^ 2 = ");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNumber) {
            if (editTextNumber.getText().toString().matches("")) {
                editTextNumber.setError("You must write something!");
            } else {
                if (Math.pow(random1, 2) == Integer.parseInt(editTextNumber.getText().toString())) {
                    stopRandom = false;
                    counterResult++;

                    if (counterResult == 5) {
                        countDownTimer.cancel();

                        saveTime(numTimer);
                    }

                    randomNumbers();
                    textNumber();

                    editTextNumber.setText("");
                } else {
                    Toast.makeText(this, "Your answer is wrong", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDownTimer.cancel();
    }

}
