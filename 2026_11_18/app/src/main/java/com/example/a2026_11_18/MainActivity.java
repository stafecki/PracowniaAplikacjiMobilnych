package com.example.a2026_11_18;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+-=";

    private final Random random = new Random();
    private String password = "";

    private EditText editFirstName;
    private EditText editLastName;
    private Spinner spinnerPosition;
    private EditText editPasswordLength;
    private CheckBox checkLetters;
    private CheckBox checkDigits;
    private CheckBox checkSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        spinnerPosition = findViewById(R.id.spinnerPosition);
        editPasswordLength = findViewById(R.id.editPasswordLength);
        checkLetters = findViewById(R.id.checkLetters);
        checkDigits = findViewById(R.id.checkDigits);
        checkSpecial = findViewById(R.id.checkSpecial);

        ((Button) findViewById(R.id.buttonGenerate)).setOnClickListener(v -> generatePassword());
        ((Button) findViewById(R.id.buttonSubmit)).setOnClickListener(v -> submit());
    }

    private char randomChar(String set) {
        return set.charAt(random.nextInt(set.length()));
    }

    private void generatePassword() {
        String lengthText = editPasswordLength.getText().toString();
        int length = lengthText.isEmpty() ? 0 : Integer.parseInt(lengthText);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(randomChar(LOWERCASE));
        }
        // Uproszczenie z zadania: stałe pozycje znaków (0 - wielka litera, 1 - cyfra, 2 - znak specjalny)
        if (checkLetters.isChecked() && length > 0) builder.setCharAt(0, randomChar(UPPERCASE));
        if (checkDigits.isChecked() && length > 1) builder.setCharAt(1, randomChar(DIGITS));
        if (checkSpecial.isChecked() && length > 2) builder.setCharAt(2, randomChar(SPECIAL));

        password = builder.toString();
        showMessage("Wygenerowane hasło: " + password);
    }

    private void submit() {
        showMessage("Imię: " + editFirstName.getText()
                + "\nNazwisko: " + editLastName.getText()
                + "\nStanowisko: " + spinnerPosition.getSelectedItem()
                + "\nHasło: " + password);
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setPositiveButton("OK", null).show();
    }
}
