package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

public class LutemonOrange extends Lutemon {
    private static final long serialVersionUID = 1L;
    public LutemonOrange(String name) {
        super(name, "Orange", 8, 1, 17, R.drawable.lutemon_orange);
    }

    @Override
    public void resetStats() {
        this.attack = 8;
        this.defense = 1;
        this.maxHealth = 17;
        this.currentHealth = maxHealth;
        this.experience = 0;
        this.trainingPoints = 0;
        this.trainingDays = 0;
    }

}