package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// The application's main screen where the user can navigate to different functionalities
public class MainActivity extends AppCompatActivity {

    // Sets up button listeners and defines navigation to different activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Navigates to the screen for adding a new Lutemon
        ImageButton addNewLutemonButton = findViewById(R.id.AddNewLutemonButton);
        addNewLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the Lutemon list view
        ImageButton listLutemonButton = findViewById(R.id.ListLutemonButton);
        listLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the Lutemon moving view (Home, Training, Fight)
        ImageButton moveLutemonButton = findViewById(R.id.MoveLutemonButton);
        moveLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoveLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the training activity
        ImageButton trainingButton = findViewById(R.id.TrainingButton);
        trainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the battle arena selection screen
        ImageButton fightArenaButton = findViewById(R.id.PickYourLutemonButton);
        fightArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickYourLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the help page
        ImageButton helpButton = findViewById(R.id.HelpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the save and load screen
        ImageButton saveLoadButton = findViewById(R.id.SaveLoadButton);
        saveLoadButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SaveLoadActivity.class);
            startActivity(intent);
        });
    }
}