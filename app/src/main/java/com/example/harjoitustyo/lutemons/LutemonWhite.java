package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonWhite is a white-colored character that inherits from the abstract Lutemon class
// Defines the initial values and reset logic for the white Lutemon
public class LutemonWhite extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Constructor
    // Creates a new white Lutemon with the given name and predefined stats
    public LutemonWhite(String name) {
        super(name, "White", 5, 4, 20, R.drawable.lutemon_white);
    }

    // Resets the Lutemon to its original values (attack, defense, health, etc.)
    // Used for example after death
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