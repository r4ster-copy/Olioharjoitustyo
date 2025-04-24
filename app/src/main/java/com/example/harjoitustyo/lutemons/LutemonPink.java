package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonPink on vaaleanpunainen Lutemon-hahmo, joka perii Lutemon-luokan.
// Määrittää pinkin Lutemonin ominaisuudet ja palauttaa ne tarvittaessa.
public class LutemonPink extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Konstruktori
    // Luo uuden pinkin Lutemonin annetulla nimellä
    public LutemonPink(String name) {
        super(name, "Pink", 7, 2, 18, R.drawable.lutemon_pink);
    }

    // Palauttaa Lutemonin alkuperäiset arvot (hyökkäys, puolustus, elämä jne.)
    // Käytetään esim. kuoleman jälkeen
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