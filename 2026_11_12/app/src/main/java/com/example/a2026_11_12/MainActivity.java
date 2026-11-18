package com.example.a2026_11_12;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekRed;
    private SeekBar seekGreen;
    private SeekBar seekBlue;
    private TextView valueRed;
    private TextView valueGreen;
    private TextView valueBlue;
    private View bigColor;
    private TextView smallColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int margin = (int) (16 * getResources().getDisplayMetrics().density);
            v.setPadding(systemBars.left + margin, systemBars.top + margin,
                    systemBars.right + margin, systemBars.bottom + margin);
            return insets;
        });

        seekRed = findViewById(R.id.seekR);
        seekGreen = findViewById(R.id.seekG);
        seekBlue = findViewById(R.id.seekB);
        valueRed = findViewById(R.id.valueR);
        valueGreen = findViewById(R.id.valueG);
        valueBlue = findViewById(R.id.valueB);
        bigColor = findViewById(R.id.bigColor);
        smallColor = findViewById(R.id.smallColor);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateBigColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        seekRed.setOnSeekBarChangeListener(listener);
        seekGreen.setOnSeekBarChangeListener(listener);
        seekBlue.setOnSeekBarChangeListener(listener);

        findViewById(R.id.pickButton).setOnClickListener(v -> saveColor());
    }

    private int currentColor() {
        return Color.rgb(seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress());
    }

    private void updateBigColor() {
        valueRed.setText(String.valueOf(seekRed.getProgress()));
        valueGreen.setText(String.valueOf(seekGreen.getProgress()));
        valueBlue.setText(String.valueOf(seekBlue.getProgress()));
        bigColor.setBackgroundColor(currentColor());
    }

    private void saveColor() {
        int color = currentColor();
        smallColor.setBackgroundColor(color);
        smallColor.setText(getString(R.string.rgb_format,
                seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress()));
        boolean darkBackground = Color.luminance(color) < 0.5;
        smallColor.setTextColor(darkBackground ? Color.WHITE : Color.BLACK);
    }
}
