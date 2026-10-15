package com.example.a2026_10_15;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int likeCount = 0;
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
        Button likeButton = findViewById(R.id.like_button);
        Button deleteButton = findViewById(R.id.delete_button);
        TextView likeCountTextView = findViewById(R.id.likes_text);

        likeButton.setOnClickListener(v -> {
            likeCount++;
            String result = likeCount + " pulubień";
            likeCountTextView.setText(result);
        });

        deleteButton.setOnClickListener(v -> {
            if (likeCount > 0) {
                likeCount--;
            }
            String result = likeCount + " pulubień";
            likeCountTextView.setText(result);
        });
    }
}