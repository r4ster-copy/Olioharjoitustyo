package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

public class LutemonWhite extends Lutemon {
    private static final long serialVersionUID = 1L;
    public LutemonWhite(String name) {

        super(name, "White", 5, 4, 20, R.drawable.lutemon_white);
    }

    @Override
    public void resetStats() {
        this.attack = 5;
        this.defense = 4;
        this.maxHealth = 20;
        this.currentHealth = maxHealth;
        this.experience = 0;
        this.trainingPoints = 0;
        this.trainingDays = 0;
    }

}