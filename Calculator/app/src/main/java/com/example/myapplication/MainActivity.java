package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
    }


    public void onNumberClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (currentInput.equals("0") || isOperatorClicked) {
            currentInput = buttonText;
            isOperatorClicked = false;
        } else {
            currentInput += buttonText;
        }
        tvResult.setText(currentInput);
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (!operator.isEmpty()) {
            calculate();
        }

        firstOperand = Double.parseDouble(currentInput);
        operator = buttonText;
        isOperatorClicked = true;
    }

    public void onEqualsClick(View view) {
        if (!operator.isEmpty()) {
            calculate();
            operator = "";
        }
    }

    private void calculate() {
        double secondOperand = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "÷":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    tvResult.setText("Error");
                    return;
                }
                break;
        }

        currentInput = String.valueOf(result);
        tvResult.setText(currentInput);
        isOperatorClicked = true;
    }
}