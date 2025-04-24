package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonGreen on vihreä Lutemon-hahmo, joka perii Lutemon-luokan.
// Määrittelee vihreän Lutemonin oletusarvot ja palauttaa ne resetoinnissa.
public class LutemonGreen extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Konstruktori
    // Luo uuden vihreän Lutemonin annetulla nimellä
    public LutemonGreen(String name) {
        super(name, "Green", 6, 3, 19, R.drawable.lutemon_green);
    }

    // Palauttaa Lutemonin alkuperäiset arvot (hyökkäys, puolustus, elämä jne.)
    // Käytetään esim. kun Lutemon kuolee
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