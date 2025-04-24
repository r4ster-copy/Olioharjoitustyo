package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonOrange on oranssi Lutemon-hahmo, joka perii Lutemon-luokan.
// Määrittää oranssin Lutemonin oletusarvot ja palauttaa ne tarvittaessa.
public class LutemonOrange extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Konstruktori
    // Luo uuden oranssin Lutemonin annetulla nimellä
    public LutemonOrange(String name) {
        super(name, "Orange", 8, 1, 17, R.drawable.lutemon_orange);
    }

    // Palauttaa Lutemonin alkuperäiset arvot (hyökkäys, puolustus, elämä jne.)
    // Käytetään esim. kuoleman jälkeen
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