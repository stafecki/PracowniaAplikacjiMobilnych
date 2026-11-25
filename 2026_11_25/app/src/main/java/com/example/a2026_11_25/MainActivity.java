package com.example.a2026_11_25;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String PESEL = "00000000000"; // TODO: numer zdającego

    private RadioGroup rgType;
    private ImageView imgType;
    private TextView tvPrice;
    private EditText etZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("Nadaj Przesyłkę, PESEL: " + PESEL);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgType = findViewById(R.id.rgType);
        imgType = findViewById(R.id.imgType);
        tvPrice = findViewById(R.id.tvPrice);
        etZip = findViewById(R.id.etZip);

        findViewById(R.id.btnCheckPrice).setOnClickListener(v -> checkPrice());
        findViewById(R.id.btnConfirm).setOnClickListener(v -> confirm());
    }

    private void checkPrice() {
        int id = rgType.getCheckedRadioButtonId();
        if (id == R.id.rbLetter) {
            imgType.setImageResource(R.drawable.list);
            tvPrice.setText("Cena: 1,5 zł");
        } else if (id == R.id.rbPackage) {
            imgType.setImageResource(R.drawable.paczka);
            tvPrice.setText("Cena: 10 zł");
        } else {
            imgType.setImageResource(R.drawable.pocztowka);
            tvPrice.setText("Cena: 1 zł");
        }
    }

    private void confirm() {
        String zip = etZip.getText().toString();
        String message;
        if (zip.length() != 5) {
            message = "Nieprawidłowa liczba cyfr w kodzie pocztowym";
        } else if (!zip.chars().allMatch(Character::isDigit)) {
            message = "Kod pocztowy powinien się składać z samych cyfr";
        } else {
            message = "Dane przesyłki zostały wprowadzone";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
