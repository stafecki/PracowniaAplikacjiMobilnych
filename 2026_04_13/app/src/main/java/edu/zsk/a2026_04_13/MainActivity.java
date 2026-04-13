package edu.zsk.a2026_04_13;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int clickCount = 0;
    private String name = "";
    private String email = "";
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

        Button button = findViewById(R.id.button);
        EditText nameInput = findViewById(R.id.name_input);
        EditText emailInput = findViewById(R.id.email_input);
        TextView txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);

        if(savedInstanceState != null){
            clickCount = savedInstanceState.getInt("counter_value", clickCount);
            name = savedInstanceState.getString("user_name", name);
            email = savedInstanceState.getString("user_email", email);
            if (!name.isEmpty() && !email.isEmpty()){
                txt1.setText(getString(R.string.welcome_message, name, email));
            }
        }

        txt2.setText(getString(R.string.counter_status, clickCount));

        button.setOnClickListener(v -> {
            if (emailInput.getText().toString().isEmpty() || nameInput.getText().toString().isEmpty()){
                Toast.makeText(this, "Najpierw uzupełnij swoje dane", Toast.LENGTH_SHORT).show();
                return;
            }
            name = nameInput.getText().toString();
            email = emailInput.getText().toString();
            String welcomeMessage = getString(R.string.welcome_message, name, email);
            txt1.setText(welcomeMessage);

            clickCount++;
            String statusText = getString(R.string.counter_status, clickCount);
            txt2.setText(statusText);
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("counter_value", clickCount);
        outState.putString("user_name", name);
        outState.putString("user_email", email);
    }
}