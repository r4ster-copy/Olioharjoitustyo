package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonOrange is an orange-colored character that inherits from the Lutemon class
// Defines default values for the orange Lutemon and resets them when needed
public class LutemonOrange extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Constructor
    // Creates a new orange Lutemon with the given name
    public LutemonOrange(String name) {
        super(name, "Orange", 8, 1, 17, R.drawable.lutemon_orange);
    }

    // Resets the Lutemon to its original values (attack, defense, health, etc.)
    // Used for example after death
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