package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonStorage;

public class UseTrainingPointActivity extends AppCompatActivity {

    private Lutemon lutemon;
    private ImageView imageView;
    private TextView nameText, attackText, defenceText, hpText, xpText, trainingPointText;
    private Button attackBtn, defenceBtn, hpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_training_point);

        // Takaisin-nappi
        ImageButton backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(UseTrainingPointActivity.this, TrainingActivity.class);
            startActivity(intent);
        });

        // Hae viittaukset näkymän elementteihin
        nameText = findViewById(R.id.LutemonName);
        imageView = findViewById(R.id.LutemonImage);
        attackText = findViewById(R.id.StatAttack);
        defenceText = findViewById(R.id.StatDefence);
        hpText = findViewById(R.id.StatHP);
        xpText = findViewById(R.id.StatXP);
        trainingPointText = findViewById(R.id.StatTrainingPoint);

        attackBtn = findViewById(R.id.IncreaseAttack);
        defenceBtn = findViewById(R.id.IncreaseDefence);
        hpBtn = findViewById(R.id.IncreaseHP);

        // Hae Lutemon nimen perusteella
        String name = getIntent().getStringExtra("LutemonName");
        for (Lutemon l : LutemonStorage.getInstance().getLutemons()) {
            if (l.getName().equals(name)) {
                lutemon = l;
                break;
            }
        }

        if (lutemon == null) {
            nameText.setText("No Lutemon");
            disableButtons();
            return;
        }

        updateStats();

        // Napit: käytä piste ja päivitä näkymä
        attackBtn.setOnClickListener(v -> {
            if (lutemon.useTrainingPoint()) {
                lutemon.increaseAttack();
                updateStats();
            }
        });

        defenceBtn.setOnClickListener(v -> {
            if (lutemon.useTrainingPoint()) {
                lutemon.increaseDefense();
                updateStats();
            }
        });

        hpBtn.setOnClickListener(v -> {
            if (lutemon.useTrainingPoint()) {
                lutemon.increaseMaxHealth();
                updateStats();
            }
        });
    }

    private void updateStats() {
        imageView.setImageResource(lutemon.getImage());
        nameText.setText(lutemon.getName());
        attackText.setText("Attack: " + lutemon.getAttack());
        defenceText.setText("Defence: " + lutemon.getDefense());
        hpText.setText("Max HP: " + lutemon.getMaxHealth());
        xpText.setText("XP :" + lutemon.getExperience());
        trainingPointText.setText("Training Points: " + lutemon.getTrainingPoints());

        boolean available = lutemon.getTrainingPoints() > 0;
        attackBtn.setEnabled(available);
        defenceBtn.setEnabled(available);
        hpBtn.setEnabled(available);
    }

    private void disableButtons() {
        attackBtn.setEnabled(false);
        defenceBtn.setEnabled(false);
        hpBtn.setEnabled(false);
    }
}
