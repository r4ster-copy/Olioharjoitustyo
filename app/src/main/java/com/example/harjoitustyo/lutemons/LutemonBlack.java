package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonBlack is a subclass of Lutemon representing the black-colored character
// Defines default values for the black Lutemon and resets them when needed
public class LutemonBlack extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Constructor
    // Creates a new black Lutemon with a name and sets its default values
    public LutemonBlack(String name) {
        super(name, "Black", 9, 0, 16, R.drawable.lutemon_black);
    }

    // Resets the Lutemon to its original values (attack, defense, health, etc.)
    // Used for example when the Lutemon dies
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