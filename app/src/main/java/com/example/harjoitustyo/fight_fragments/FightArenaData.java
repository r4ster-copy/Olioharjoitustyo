package com.example.harjoitustyo.fight_fragments;

import android.util.Log;
import com.example.harjoitustyo.lutemons.Lutemon;

public class FightArenaData {
    private static FightArenaData instance;

    private Lutemon fighter1;
    private Lutemon fighter2;
    private Lutemon winner;

    private FightArenaData() {}

    public static FightArenaData getInstance() {
        if (instance == null) {
            instance = new FightArenaData();
        }
        return instance;
    }

    public void setFighters(Lutemon l1, Lutemon l2) {
        this.fighter1 = l1;
        this.fighter2 = l2;
        Log.d("FightArenaData", "Fighters set: " + l1.getName() + " and " + l2.getName());
    }

    public Lutemon getFighter1() {
        return fighter1;
    }

    public Lutemon getFighter2() {
        return fighter2;
    }

    public void setWinner(Lutemon winner) {
        this.winner = winner;
    }

    public Lutemon getWinner() {
        return winner;
    }
}

