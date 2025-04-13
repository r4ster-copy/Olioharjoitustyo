package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageButton addNewLutemonButton = findViewById(R.id.AddNewLutemonButton);
        addNewLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewLutemonActivity.class);
                startActivity(intent);
            }
        });

        ImageButton listLutemonButton = findViewById(R.id.ListLutemonButton);
        listLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListLutemonActivity.class);
                startActivity(intent);
            }
        });

        ImageButton moveLutemonButton = findViewById(R.id.MoveLutemonButton);
        moveLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoveLutemonActivity.class);
                startActivity(intent);
            }
        });

        ImageButton trainingButton = findViewById(R.id.TrainingButton);
        trainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(intent);
            }
        });

        ImageButton fightArenaButton = findViewById(R.id.PickYourLutemonButton);
        fightArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickYourLutemonActivity.class);
                startActivity(intent);
            }
        });

        ImageButton helpButton = findViewById(R.id.HelpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }
}
