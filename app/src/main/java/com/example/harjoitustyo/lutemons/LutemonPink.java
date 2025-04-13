package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

public class LutemonPink extends Lutemon {
    public LutemonPink(String name) {

        super(name, "Pink", 7, 2, 18, R.drawable.lutemon_pink);
    }

    @Override
    public void resetStats() {
        this.attack = 7;
        this.defense = 2;
        this.maxHealth = 18;
        this.currentHealth = maxHealth;
        this.experience = 0;
        this.trainingPoints = 0;
        this.trainingDays = 0;
    }

}