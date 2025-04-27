package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonGreen is a green-colored character that inherits from the Lutemon class
// Defines default values for the green Lutemon and resets them when needed
public class LutemonGreen extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Constructor
    // Creates a new green Lutemon with the given name
    public LutemonGreen(String name) {
        super(name, "Green", 6, 3, 19, R.drawable.lutemon_green);
    }

    // Resets the Lutemon to its original values (attack, defense, health, etc.)
    // Used for example when the Lutemon dies
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