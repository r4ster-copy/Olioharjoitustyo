package com.example.harjoitustyo.move_fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MoveLutemonActivityTabAdapter extends FragmentStateAdapter {

    public MoveLutemonActivityTabAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0: return new FragmentHome();
            case 1: return new FragmentTraining();
            case 2: return new FragmentFight();
            default: return new FragmentHome();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
