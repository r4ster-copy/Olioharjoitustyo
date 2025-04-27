package com.example.harjoitustyo.lutemons;

import java.util.ArrayList;

// A single storage location for all Lutemons, implements the singleton pattern
// Used to manage Lutemon objects (add, remove, retrieve)
public class LutemonStorage {
    private static LutemonStorage lutemonStorage = null;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    // Private constructor
    // Prevents direct instantiation from outside
    private LutemonStorage() {}

    // Returns the single existing instance
    public static LutemonStorage getInstance() {
        if (lutemonStorage == null) {
            lutemonStorage = new LutemonStorage();
        }
        return lutemonStorage;
    }

    // Adds a given Lutemon to the storage
    public void addLutemon(Lutemon l) {
        lutemons.add(l);
    }

    // Returns all stored Lutemons
    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    // Removes a given Lutemon from the storage
    public void removeLutemon(Lutemon l) {
        lutemons.remove(l);
    }

    // Clears all Lutemons from storage
    public void clearAll() {
        lutemons.clear();
    }
}