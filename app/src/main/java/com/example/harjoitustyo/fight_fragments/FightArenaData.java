package com.example.harjoitustyo.fight_fragments;

import android.util.Log;
import com.example.harjoitustyo.lutemons.Lutemon;

// Maintains information about Lutemons involved in a single battle
public class FightArenaData {
    private static FightArenaData instance;

    private Lutemon fighter1;
    private Lutemon fighter2;
    private Lutemon winner;

    // Private constructor prevents direct instantiation
    private FightArenaData() {}

    // Returns the singleton instance, creating it if necessary
    public static FightArenaData getInstance() {
        if (instance == null) {
            instance = new FightArenaData();
        }
        return instance;
    }

    // Sets the Lutemons participating in the battle
    // l1: first fighter
    // l2: second fighter
    public void setFighters(Lutemon l1, Lutemon l2) {
        this.fighter1 = l1;
        this.fighter2 = l2;
        Log.d("FightArenaData", "Fighters set: " + l1.getName() + " and " + l2.getName());
    }

    // Returns the first fighter
    public Lutemon getFighter1() {
        return fighter1;
    }

    // Returns the second fighter
    public Lutemon getFighter2() {
        return fighter2;
    }

    // Sets the winner of the battle
    public void setWinner(Lutemon winner) {
        this.winner = winner;
    }

    // Returns the winner of the battle
    public Lutemon getWinner() {
        return winner;
    }
}