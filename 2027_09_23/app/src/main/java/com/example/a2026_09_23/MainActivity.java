package com.example.a2026_09_23;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String[] quotes = {
            "Dzień dobry",
            "Good morning",
            "Buenos dias"
    };
    int index = 0;

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

        Button button = findViewById(R.id.btn);
        TextView sizeTextView = findViewById(R.id.sizeTextView);
        TextView quoteTextView = findViewById(R.id.quoteTextView);
        SeekBar seekBar = findViewById(R.id.seekbar);

        button.setOnClickListener(v->{
            if(index < quotes.length-1){
                index++;
                quoteTextView.setText(quotes[index]);
            }
            else{
                index = 0;
                quoteTextView.setText(quotes[index]);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String sizeText = "Rozmiar: " + progress;
                sizeTextView.setText(sizeText);
                quoteTextView.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}