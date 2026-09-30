package com.example.a2026_09_16;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    boolean clicked = false;

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

        Button confirmButton = findViewById(R.id.confirmButton);
        Button startButton = findViewById(R.id.startButton);
        EditText inputEditText = findViewById(R.id.laundryNumberInput);
        TextView resultTextView = findViewById(R.id.laundryNumberLabel);
        TextView vacuumStateTextView = findViewById(R.id.vacuumStateLabel);


        confirmButton.setOnClickListener(v->{
            int laundryNumber = Integer.parseInt(inputEditText.getText().toString());

            if (laundryNumber >= 1 && laundryNumber <= 12){
                String text = "Numer prania: " + laundryNumber;
                resultTextView.setText(text);
            }
        });

        startButton.setOnClickListener(v->{
            if (!clicked){
                startButton.setText("Wyłącz");
                vacuumStateTextView.setText("Odkurzacz włączony");
                clicked = true;
            }
            else{
                startButton.setText("Włącz");
                vacuumStateTextView.setText("Odkurzacz wyłączony");
                clicked = false;
            }
        });
    }
}