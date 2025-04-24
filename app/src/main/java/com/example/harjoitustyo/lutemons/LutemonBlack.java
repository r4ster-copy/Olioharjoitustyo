package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonBlack on mustan värinen Lutemon-luokan perivä hahmo.
// Määrittelee mustalle Lutemonille vakioarvot ja palauttaa ne resetoinnissa.
public class LutemonBlack extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Konstruktori
    // Luo uuden Lutemonin nimellä ja asettaa valmiit arvot mustalle Lutemonille
    public LutemonBlack(String name) {
        super(name, "Black", 9, 0, 16, R.drawable.lutemon_black);
    }

    // Palauttaa Lutemonin alkuperäiset arvot (hyökkäys, puolustus, elämä jne.)
    // Käytetään esim. kun Lutemon kuolee
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