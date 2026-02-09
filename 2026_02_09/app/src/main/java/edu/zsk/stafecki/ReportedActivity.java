package edu.zsk.stafecki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported);

        TextView nameTextView = findViewById(R.id.reported_name);
        TextView surnameTextView = findViewById(R.id.reported_surname);
        TextView classTextView = findViewById(R.id.reported_class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameTextView.setText(extras.getString("name"));
            surnameTextView.setText(extras.getString("surname"));
            classTextView.setText(extras.getString("class"));
        }

        Button backButton = findViewById(R.id.save_note_button);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
