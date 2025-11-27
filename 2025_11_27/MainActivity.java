package edu.stafec.a2025_11_27;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.Toast;

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

        Button button1 = findViewById(R.id.button2);
        EditText editText2 = findViewById(R.id.editText2);
        button1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String textForToast = editText2.getText().toString();
                        Toast.makeText(MainActivity.this, textForToast, Toast.LENGTH_SHORT).show();                    }
                }
        );
    }

    public void copyAndPasteText(View view) {
        EditText editText1 = findViewById(R.id.editText1);
        TextView textView1 = findViewById(R.id.textView1);

        String text = editText1.getText().toString();

        textView1.setText(text);
    }
}