package com.example.a2026_10_28;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final int ALPHABET_LENGTH = 26;
    private static final int DEFAULT_KEY = 0;
    private static final String DEFAULT_FILE = "szyfr.txt";

    private EditText keyEditText;
    private EditText valueEditText;
    private TextView resultTextView;
    private Button encryptButton;
    private Button saveToFileButton;

    private final ActivityResultLauncher<String> saveFileLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.CreateDocument("text/plain"),
                    fileUri -> {
                        if (fileUri == null) {
                            return;
                        }
                        try (OutputStream outputStream = getContentResolver().openOutputStream(fileUri)) {
                            if (outputStream == null) {
                                throw new IOException("Nie udało się otworzyć pliku");
                            }
                            String encryptedText = resultTextView.getText().toString();
                            outputStream.write(encryptedText.getBytes(StandardCharsets.UTF_8));
                            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Toast.makeText(this, R.string.save_error, Toast.LENGTH_SHORT).show();
                        }
                    });
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
        keyEditText = findViewById(R.id.key_value_edit_text);
        valueEditText = findViewById(R.id.text_value_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        encryptButton = findViewById(R.id.encrypt_button);
        saveToFileButton = findViewById(R.id.save_button);


        encryptButton.setOnClickListener(v->{
            int key;
            try {
                key = Integer.parseInt(keyEditText.getText().toString().trim());
            } catch (NumberFormatException e) {
                key = 0;
            }

            int shift = Math.floorMod(key, ALPHABET_LENGTH);
            String plainText = valueEditText.getText().toString();
            StringBuilder encryptedText = new StringBuilder();

            for (char character : plainText.toCharArray()) {
                if (character >= 'a' && character <= 'z') {
                    encryptedText.append((char) ('a' + (character - 'a' + shift) % ALPHABET_LENGTH));
                } else if (character >= 'A' && character <= 'Z') {
                    encryptedText.append((char) ('A' + (character - 'A' + shift) % ALPHABET_LENGTH));
                } else {
                    encryptedText.append(character);
                }
            }

            resultTextView.setText(encryptedText.toString());
        });

        saveToFileButton.setOnClickListener(v->{
            if (resultTextView.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.nothing_to_save, Toast.LENGTH_SHORT).show();
                return;
            }
            saveFileLauncher.launch(DEFAULT_FILE);
        });
    }
}