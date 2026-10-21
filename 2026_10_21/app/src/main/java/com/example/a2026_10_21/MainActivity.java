package com.example.a2026_10_21;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String[] animals = {"Pies", "Kot", "Świnka morska"};
    private String selectedElement;
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


        ListView listView = findViewById(R.id.listView);
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText visitEditText = findViewById(R.id.visitEditText);
        SeekBar seekBar = findViewById(R.id.ageSeekBar);
        Button okButton = findViewById(R.id.submitButton);
        TextView ageTextView = findViewById(R.id.ageTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        EditText timePicker = findViewById(R.id.visitHourEditText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, animals);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedElement = (String) listView.getItemAtPosition(position);

                if (selectedElement.equals("Pies")){
                    seekBar.setMax(18);
                }
                else if (selectedElement.equals("Kot")){
                    seekBar.setMax(20);
                }
                else if (selectedElement.equals("Świnka morska")){
                    seekBar.setMax(9);
                }

                if (seekBar.getProgress() > seekBar.getMax()){
                    seekBar.setProgress(seekBar.getMax());
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageTextView.setText(String.valueOf(seekBar.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        okButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String visit = visitEditText.getText().toString();
            String age = String.valueOf(seekBar.getProgress());
            String time = timePicker.getText().toString();

            String result = name + ", " + selectedElement + ", " + age + " , " + visit + ", " + time;

            resultTextView.setText(result);
        });

    }
}
