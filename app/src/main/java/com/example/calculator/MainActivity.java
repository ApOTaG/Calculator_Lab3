package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputField;
    private String currentInput = "";
    private double currentValue = 0.0;
    private String currentOperator = "";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
    }

public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("Clear")) {

            currentInput = "";
            currentOperator = "";
            currentValue = 0.0;
        } else if (buttonText.equals("+/-")) {
            if (!currentInput.isEmpty()) {

                double number = Double.parseDouble(currentInput);
                currentInput = String.valueOf(-number);
            }
        } else if (isOperator(buttonText)) {
            if (!currentInput.isEmpty()) {

                if (!currentOperator.isEmpty()) {
                    calculateResult();
                }

                currentOperator = buttonText;

                currentValue = Double.parseDouble(currentInput);

                currentInput = "";
            }
        } else {
            currentInput += buttonText;
        }

        inputField.setText(currentInput);
    }

public void onEqualsClick(View view) {
        if (!currentInput.isEmpty() && !currentOperator.isEmpty()) {
            calculateResult();
        }
    }

private void calculateResult() {
        double secondValue = Double.parseDouble(currentInput);
        double result = 0.0;

        switch (currentOperator) {
            case "+":
                result = currentValue + secondValue;
                break;
            case "-":
                result = currentValue - secondValue;
                break;
            case "*":
                result = currentValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = currentValue / secondValue;
                } else {
                    inputField.setText("Error: Division by zero");
                    return;
                }
                break;
            case "√":
                if (currentValue >= 0) {
                    result = Math.sqrt(currentValue);
                } else {
                    inputField.setText("Error: Invalid operation");
                    return;
                }
                break;
        }

        currentValue = result;
        currentInput = "";
        currentOperator = "";
        inputField.setText(String.valueOf(result));
    }

    private boolean isOperator(String text) {
        return text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") || text.equals("√");
    }
}
