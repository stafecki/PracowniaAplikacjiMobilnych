package com.example.a2027_09_03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String[] dices = {"k1", "k2", "k3", "k4", "k5", "k6"};
    private ImageView[] diceImages;

    int finalScore = 0;
    int score = 0;


    private int calculateScore(int[] diceValues){
        int score = 0;
        Map<Integer, Integer> counter = new HashMap<>();

        for (int value : diceValues) {
            counter.put(value, counter.getOrDefault(value, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int count = entry.getValue();
            int value = entry.getKey();

            if (count >= 2){
                score+= value * count;
            }
        }

        return score;
    }

    private void setImages(int[] diceValues){
        for(int i = 0; i < diceValues.length; i++){
            String resName = dices[diceValues[i] - 1];
            int resId = getResources().getIdentifier(resName, "drawable", getPackageName());
            diceImages[i].setImageResource(resId);
        }
    }

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

        diceImages = new ImageView[]{
                findViewById(R.id.diceImage1),
                findViewById(R.id.diceImage2),
                findViewById(R.id.diceImage3),
                findViewById(R.id.diceImage4),
                findViewById(R.id.diceImage5)
        };

        Random rand = new Random();
        Button throwButton = findViewById(R.id.throwButton);
        Button resetButton = findViewById(R.id.resetButton);
        TextView resultTextView = findViewById(R.id.throwResult);
        TextView finalResultTextView = findViewById(R.id.finalResult);

        throwButton.setOnClickListener(v -> {
            int[] randomNumbers = new int[5];
            for(int i = 0; i < 5; i++) {
                randomNumbers[i] = rand.nextInt(6) + 1;
            }
            score = calculateScore(randomNumbers);
            finalScore += score;
            resultTextView.setText("Wynik tego losowania: " + score);
            finalResultTextView.setText("Wynik gry: " + finalScore);
            setImages(randomNumbers);
        });

        resetButton.setOnClickListener(v->{
            finalScore = 0;
            score = 0;
            resultTextView.setText("Wynik tego losowania: " + score);
            finalResultTextView.setText("Wynik gry: " + finalScore);
            for (int i = 0; i < diceImages.length; i++) {
                diceImages[i].setImageResource(R.drawable.question);
            }
        });

    }
}