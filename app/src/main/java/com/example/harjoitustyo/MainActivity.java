package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// Sovelluksen aloitusnäkymä, josta käyttäjä voi siirtyä eri toiminnallisuuksiin
public class MainActivity extends AppCompatActivity {

    // Asettaa painikkeiden kuuntelijat ja määrittää siirtymät eri aktiviteetteihin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Siirtyy uutta Lutemonia lisäävään näkymään
        ImageButton addNewLutemonButton = findViewById(R.id.AddNewLutemonButton);
        addNewLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Siirtyy Lutemon-listanäkymään
        ImageButton listLutemonButton = findViewById(R.id.ListLutemonButton);
        listLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Siirtyy Lutemonien siirtonäkymään (Home, Training, Fight)
        ImageButton moveLutemonButton = findViewById(R.id.MoveLutemonButton);
        moveLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoveLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Siirtyy treeniaktiviteettiin
        ImageButton trainingButton = findViewById(R.id.TrainingButton);
        trainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(intent);
            }
        });

        // Siirtyy taisteluareenan valintanäkymään
        ImageButton fightArenaButton = findViewById(R.id.PickYourLutemonButton);
        fightArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickYourLutemonActivity.class);
                startActivity(intent);
            }
        });

        // Siirtyy ohjesivulle
        ImageButton helpButton = findViewById(R.id.HelpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        // Siirtyy tallennus- ja latausnäkymään
        ImageButton saveLoadButton = findViewById(R.id.SaveLoadButton);
        saveLoadButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SaveLoadActivity.class);
            startActivity(intent);
        });
    }
}