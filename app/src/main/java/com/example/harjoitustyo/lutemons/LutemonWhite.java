package com.example.harjoitustyo.lutemons;

import com.example.harjoitustyo.R;

// LutemonWhite on valkoinen Lutemon-hahmo, joka perii abstraktin Lutemon-luokan.
// Määrittelee valkoisen Lutemonin alkuarvot ja palautuslogiikan.
public class LutemonWhite extends Lutemon {
    private static final long serialVersionUID = 1L;

    // Konstruktori
    // Luo uuden valkoisen Lutemonin annetulla nimellä ja valmiilla statseilla
    public LutemonWhite(String name) {
        super(name, "White", 5, 4, 20, R.drawable.lutemon_white);
    }

    // Palauttaa Lutemonin alkuperäiset arvot (hyökkäys, puolustus, elämä jne.)
    // Käytetään esim. kuoleman jälkeen
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