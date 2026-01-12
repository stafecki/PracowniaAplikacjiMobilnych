package edu.stafec.a2026_01_12;

import android.content.Intent;
import android.graphics.Color;
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
                        /*if (!emailText.contains("@")){
                            result.setText("Nieprawidłowy adres e-mail");
                            return;
                        } - to bylo w zadaniu inf04, zmienione na regex*/
                        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                            showErrorMessage("Nieprawidłowy adres e-mail", result);
                            return;
                        }
                        if(!pass1.equals(pass2)){
                            showErrorMessage("Hasła różnią się", result);
                            return;
                        }
                        if(!isValidPassword(pass1)){
                            showErrorMessage("Hasło musi mieć co najmniej 8 znaków, zawierać literę, wielką literę i cyfrę", result);
                            return;
                        }

                        result.setText("Witaj " + emailText);

                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        intent.putExtra("email", emailText);
                        startActivity(intent);
                    }
                }
        );

    }
    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        boolean hasLetter = false;
        boolean hasBigLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLetter = true;
            else if (Character.isUpperCase(c)) hasBigLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            if (hasLetter && hasDigit && hasBigLetter) return true;
        }
        return false;
    }

    private void showErrorMessage(String message, TextView result) {
        result.setText(message);
        result.setTextColor(Color.RED);
    }
}