package com.banglaappstanvir.lovestory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private TextView textResult, textResultTitle;
    private View btnCheck, btnShare, btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        textResult = findViewById(R.id.textResult);
        textResultTitle = findViewById(R.id.textResultTitle);
        btnCheck = findViewById(R.id.btnCheck);
        btnShare = findViewById(R.id.btnShare);
        btnRestart = findViewById(R.id.btnRestart);

        btnCheck.setOnClickListener(v -> checkPrime());
        btnRestart.setOnClickListener(v -> resetApp());
        btnShare.setOnClickListener(v -> shareResult());
    }

    private void checkPrime() {
        String numStr = inputNumber.getText().toString().trim();
        if (numStr.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        long number;
        try {
            number = Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isPrime = isPrime(number);
        textResult.setText(number + (isPrime ? " is a Prime Number" : " is NOT a Prime Number"));
        textResult.setVisibility(View.VISIBLE);
        textResultTitle.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.VISIBLE);
        btnRestart.setVisibility(View.VISIBLE);
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private void resetApp() {
        inputNumber.setText("");
        textResult.setText("Result will appear here");
        textResult.setVisibility(View.VISIBLE); // keep placeholder visible
        textResultTitle.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.GONE);
        btnRestart.setVisibility(View.GONE);
    }

    private void shareResult() {
        String result = textResult.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, result);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}
