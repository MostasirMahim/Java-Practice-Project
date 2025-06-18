package com.example.calculatorm;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorm.R;

public class MainActivity extends AppCompatActivity {

    private EditText display; // To show the entered numbers and result
    private String currentInput = ""; // Stores the current input
    private String operator = ""; // Stores the last selected operator
    private double firstOperand = 0.0; // Stores the first operand

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize display
        display = findViewById(R.id.display);

        // Set up button listeners for digits
        setNumberListeners();

        // Set up operator listeners (+, -, *, /)
        setOperatorListeners();

        // Set up other buttons (C, =, ⌫)
        setUtilityListeners();
    }

    private void setNumberListeners() {
        int[] numberIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_dot
        };

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            currentInput += button.getText().toString();
            display.setText(currentInput);
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorListeners() {
        int[] operatorIds = {R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide};

        View.OnClickListener listener = v -> {
            if (!currentInput.isEmpty()) {
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
                operator = ((Button) v).getText().toString();
                display.setText(operator); // Show operator for clarity
            }
        };

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setUtilityListeners() {
        // Clear button
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            currentInput = "";
            operator = "";
            firstOperand = 0.0;
            display.setText("");
        });

        // Delete button
        findViewById(R.id.btn_delete).setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                display.setText(currentInput);
            }
        });

        // Equals button
        findViewById(R.id.btn_equals).setOnClickListener(v -> {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = performCalculation(firstOperand, secondOperand, operator);
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operator = "";
            }
        });
    }

    private double performCalculation(double first, double second, String op) {
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return second != 0 ? first / second : 0; // Prevent division by zero
            default:
                return 0;
        }
    }
}
