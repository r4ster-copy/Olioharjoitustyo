package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

public class LutemonBlack extends Lutemon {
    private static final long serialVersionUID = 1L;
    public LutemonBlack(String name) {

        super(name, "Black", 9, 0, 16, R.drawable.lutemon_black);
    }

    @Override
    public void resetStats() {
        this.attack = 9;
        this.defense = 0;
        this.maxHealth = 16;
        this.currentHealth = maxHealth;
        this.experience = 0;
        this.trainingPoints = 0;
        this.trainingDays = 0;
    }

}