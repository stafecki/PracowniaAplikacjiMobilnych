package edu.zsk.stafecki;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        Button reportButton = findViewById(R.id.save_note_button);
        EditText reportName = findViewById(R.id.name_input);
        EditText reportSurname = findViewById(R.id.surname_input);
        EditText reportClass = findViewById(R.id.class_input);

        reportButton.setOnClickListener(v -> {
            String name = reportName.getText().toString();
            String surname = reportSurname.getText().toString();
            String className = reportClass.getText().toString();
            Log.d("MainActivity", "Name: " + name + ", Surname: " + surname + ", Class: " + className);

            if(name.isEmpty() || surname.isEmpty() || className.isEmpty()) {
                Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
            }
            else{
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Dodaję uwagę...");
                progressDialog.setMessage("Proszę czekać.");
                progressDialog.setCancelable(false);
                progressDialog.show();

                reportName.setText("");
                reportSurname.setText("");
                reportClass.setText("");

                new Handler().postDelayed(() -> {
                    progressDialog.dismiss();
                    Intent intent = new Intent(this, ReportedActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("surname", surname);
                    intent.putExtra("className", className);
                    startActivity(intent);
                }, 2000);

            }
        });
    }
}