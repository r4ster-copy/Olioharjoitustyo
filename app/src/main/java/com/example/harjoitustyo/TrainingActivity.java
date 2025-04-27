package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonStorage;

import java.util.ArrayList;

// Activity where a single Lutemon is trained and collected training points are used
public class TrainingActivity extends AppCompatActivity {

    private Lutemon selectedLutemon;
    private RadioGroup lutemonRadioGroup;
    private ImageView imageView;
    private TextView nameText, attackText, defenceText, hpText, xpText, trainingPointText, trainingText, weatherText;
    private Button trainButton, useTrainingPointButton;
    private ArrayList<Lutemon> trainingLutemons;

    // Initializes the view and sets up the Lutemons available for training
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
            startActivity(intent);
        });

        lutemonRadioGroup = findViewById(R.id.LutemonRadioGroup);
        imageView = findViewById(R.id.LutemonImage);
        nameText = findViewById(R.id.LutemonName);
        attackText = findViewById(R.id.StatAttack);
        defenceText = findViewById(R.id.StatDefence);
        hpText = findViewById(R.id.StatHP);
        xpText = findViewById(R.id.StatXP);
        trainingPointText = findViewById(R.id.StatTrainingPoint);
        trainingText = findViewById(R.id.TrainingText);
        weatherText = findViewById(R.id.WeatherText);
        trainButton = findViewById(R.id.TrainButton);
        useTrainingPointButton = findViewById(R.id.UseTrainingPointButton);

        // Collect all Lutemons whose location is "training"
        trainingLutemons = new ArrayList<>();
        for (Lutemon l : LutemonStorage.getInstance().getLutemons()) {
            if (l.getLocation().equals("training")) {
                trainingLutemons.add(l);
            }
        }

        populateRadioGroup();

        // Starts training and adds training points (possibly double if the weather favors)
        trainButton.setOnClickListener(v -> {
            if (selectedLutemon == null) return;

            trainingText.setVisibility(View.VISIBLE);
            trainingText.setText("Training...");
            trainButton.setEnabled(false);

            CheckWeather.getWeatherBonus(selectedLutemon, (isFavorable, temp, weather) -> {
                runOnUiThread(() -> {
                    new Handler().postDelayed(() -> {
                        weatherText.setText("Lappeenranta training weather: " + temp + "°C, " + weather);
                        int addedpoints = isFavorable ? 2 : 1;
                        selectedLutemon.addTrainingPoint(addedpoints);
                        selectedLutemon.incrementTrainingDays();
                        trainingText.setText(isFavorable ? "The weather in Lappeenranta favors your Lutemon! Training points added: " + addedpoints : "Done! Training points added: " + addedpoints);
                        new Handler().postDelayed(() -> trainingText.setText(""), 5000);
                        updateLutemonInfo();
                        trainButton.setEnabled(true);
                    }, 3000);
                });
            });
        });

        // Moves to a separate screen to use training points
        useTrainingPointButton.setOnClickListener(v -> {
            Intent intent = new Intent(TrainingActivity.this, UseTrainingPointActivity.class);
            intent.putExtra("LutemonName", selectedLutemon.getName());
            startActivity(intent);
        });
    }

    // Creates radio buttons for each Lutemon available for training
    private void populateRadioGroup() {
        lutemonRadioGroup.removeAllViews();

        for (int i = 0; i < trainingLutemons.size(); i++) {
            Lutemon lutemon = trainingLutemons.get(i);
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
            radioButton.setId(View.generateViewId());

            int finalI = i;
            radioButton.setOnClickListener(v -> {
                selectedLutemon = trainingLutemons.get(finalI);
                updateLutemonInfo();
            });

            lutemonRadioGroup.addView(radioButton);
        }
    }

    // Updates the selected Lutemon's stats on the screen
    private void updateLutemonInfo() {
        if (selectedLutemon == null) return;

        imageView.setImageResource(selectedLutemon.getImage());
        nameText.setText(selectedLutemon.getName());
        attackText.setText("Attack: " + selectedLutemon.getAttack());
        defenceText.setText("Defence: " + selectedLutemon.getDefense());
        hpText.setText("HP: " + selectedLutemon.getCurrentHealth() + " / " + selectedLutemon.getMaxHealth());
        xpText.setText("XP: " + selectedLutemon.getExperience());
        trainingPointText.setText("Training Points: " + selectedLutemon.getTrainingPoints());
        useTrainingPointButton.setEnabled(false);
        useTrainingPointButton.setEnabled(selectedLutemon.getTrainingPoints() > 0);
    }
}