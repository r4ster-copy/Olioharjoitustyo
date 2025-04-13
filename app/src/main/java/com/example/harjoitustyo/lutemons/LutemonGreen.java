package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

public class LutemonGreen extends Lutemon {
    public LutemonGreen(String name) {

        super(name, "Green", 6, 3, 19, R.drawable.lutemon_green);

    }

    @Override
    public void resetStats() {
        this.attack = 6;
        this.defense = 3;
        this.maxHealth = 19;
        this.currentHealth = maxHealth;
        this.experience = 0;
        this.trainingPoints = 0;
        this.trainingDays = 0;
    }
}


