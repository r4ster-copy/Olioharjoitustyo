package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonPink is a pink-colored character that inherits from the Lutemon class
// Defines attributes for the pink Lutemon and resets them when needed
public class LutemonPink extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Constructor
    // Creates a new pink Lutemon with the given name
    public LutemonPink(String name) {
        super(name, "Pink", 7, 2, 18, R.drawable.lutemon_pink);
    }

    // Resets the Lutemon to its original values (attack, defense, health, etc.)
    // Used for example after death
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