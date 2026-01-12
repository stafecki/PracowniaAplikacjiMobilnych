package edu.stafec.a2026_01_12;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

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
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        EditText confirmPassword = findViewById(R.id.editTextPassword2);
        TextView result = findViewById(R.id.textViewResult);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String emailText = email.getText().toString();
                        String pass1 = password.getText().toString();
                        String pass2 = confirmPassword.getText().toString();
                        if (!emailText.contains("@")){
                            result.setText("Nieprawidłowy adres e-mail");
                            return;
                        }
                        if(!pass1.equals(pass2)){
                            result.setText("Hasła różnią się");
                            return;
                        }
                        result.setText("Witaj " + emailText);
                    }
                }
        );
    }
}