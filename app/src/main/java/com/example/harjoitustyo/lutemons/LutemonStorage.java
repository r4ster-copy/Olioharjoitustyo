package com.example.harjoitustyo.lutemons;

import java.util.ArrayList;

// Yksittäinen säilytyspaikka kaikille Lutemoneille. Toteuttaa singleton-mallin.
// Käytetään Lutemon-olioiden hallintaan (lisäys, poisto, haku).
public class LutemonStorage {
    private static LutemonStorage lutemonStorage = null;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    // Yksityinen konstruktori
    // Estää olion suoran luomisen ulkopuolelta
    private LutemonStorage() {}

    // Palauttaa ainoan olemassa olevan instanssin
    public static LutemonStorage getInstance() {
        if (lutemonStorage == null) {
            lutemonStorage = new LutemonStorage();
        }
        return lutemonStorage;
    }

    // Lisää annetun Lutemonin säilytykseen
    public void addLutemon(Lutemon l) {
        lutemons.add(l);
    }

    // Palauttaa kaikki tallennetut Lutemonit
    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    // Poistaa annetun Lutemonin säilytyksestä
    public void removeLutemon(Lutemon l) {
        lutemons.remove(l);
    }

    // Tyhjentää säilytyksen kaikista Lutemoneista
    public void clearAll() {
        lutemons.clear();
    }
}