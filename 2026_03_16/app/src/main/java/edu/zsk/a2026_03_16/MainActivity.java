package edu.zsk.a2026_03_16;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String[] SPECIES = {
            "Pies",
            "Kot",
            "Świnka morska"
    };
    private String selectedSpecies = "";
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

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SPECIES);

        final ListView speciesList = findViewById(R.id.speciesList);
        final SeekBar seekBar = findViewById(R.id.ageSeekBar);
        final TextView ageTextView = findViewById(R.id.ageTextView);
        final Button button = findViewById(R.id.button);
        final TextView summaryTextView = findViewById(R.id.summary);

        speciesList.setAdapter(adapter);

        speciesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long myLong){
                selectedSpecies = SPECIES[myItemInt];
                if(selectedSpecies.equals("Pies")){
                    seekBar.setMax(18);
                }else if(selectedSpecies.equals("Kot")){
                    seekBar.setMax(20);
                }
                else if(selectedSpecies.equals("Świnka morska")){
                    seekBar.setMax(9);
                }

                seekBar.setProgress(0);
                ageTextView.setText("0");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(v->{
            String ownerInfo = ((EditText) findViewById(R.id.ownerInput)).getText().toString();

            String age = ageTextView.getText().toString();
            String symptoms = ((EditText) findViewById(R.id.symptomsInput)).getText().toString();
            String time = ((EditText) findViewById(R.id.timeInput)).getText().toString();

            String summary = ownerInfo + ", " + selectedSpecies + ", " + age + " , " + symptoms + ", " + time + " ";

            summaryTextView.setText(summary);

            NotificationChannel channel = new NotificationChannel("my_channel_id", "My Channel", android.app.NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(this, "my_channel_id")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Zgłoszenie przyjęte")
                    .setContentText(summary)
                    .build();
            manager.notify(1, notification);
        });
    }
}