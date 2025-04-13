package com.example.harjoitustyo.lutemons;

import java.util.ArrayList;

public class LutemonStorage {
    private static LutemonStorage lutemonStorage = null;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    private LutemonStorage() {}

    public static LutemonStorage getInstance() {
        if (lutemonStorage == null) {
            lutemonStorage = new LutemonStorage();
        }
        return lutemonStorage;
    }

    public void addLutemon(Lutemon l) {
        lutemons.add(l);
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void removeLutemon(Lutemon l) {
        lutemons.remove(l);
    }

    public void clearAll() {
        lutemons.clear();
    }
}
