package com.example.harjoitustyo.fight_fragments;

import android.util.Log;
import com.example.harjoitustyo.lutemons.Lutemon;

// Ylläpitää taistelussa käytettyjen Lutemonien tietoja yhden taistelun ajaksi
public class FightArenaData {
    private static FightArenaData instance;

    private Lutemon fighter1;
    private Lutemon fighter2;
    private Lutemon winner;

    // Yksityinen konstruktori estää suoran olion luonnin
    private FightArenaData() {}

    // Palauttaa singleton-instanssin, luo sen tarvittaessa
    public static FightArenaData getInstance() {
        if (instance == null) {
            instance = new FightArenaData();
        }
        return instance;
    }

    // Asettaa taisteluun osallistuvat Lutemonit
    // l1: ensimmäinen taistelija
    // l2: toinen taistelija
    public void setFighters(Lutemon l1, Lutemon l2) {
        this.fighter1 = l1;
        this.fighter2 = l2;
        Log.d("FightArenaData", "Fighters set: " + l1.getName() + " and " + l2.getName());
    }

    // Palauttaa ensimmäisen taistelijan
    public Lutemon getFighter1() {
        return fighter1;
    }

    // Palauttaa toisen taistelijan
    public Lutemon getFighter2() {
        return fighter2;
    }

    // Asettaa taistelun voittajan
    public void setWinner(Lutemon winner) {
        this.winner = winner;
    }

    // Palauttaa taistelun voittajan
    public Lutemon getWinner() {
        return winner;
    }
}