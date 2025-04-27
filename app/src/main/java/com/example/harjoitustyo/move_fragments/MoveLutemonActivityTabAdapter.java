package com.example.harjoitustyo.move_fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// Adapter for ViewPager2 that switches between Home, Training, and Fight fragments
public class MoveLutemonActivityTabAdapter extends FragmentStateAdapter {

    // Creates the adapter and attaches it to the FragmentActivity
    // fa: the active fragment activity
    public MoveLutemonActivityTabAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    // Returns the desired fragment based on the position
    // position: requested page
    // returns: Fragment object (Home, Training, or Fight)
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0: return new FragmentHome();
            case 1: return new FragmentTraining();
            case 2: return new FragmentFight();
            default: return new FragmentHome(); // for safety
        }
    }

    // Returns the total number of fragments
    // returns: 3 (Home, Training, Fight)
    @Override
    public int getItemCount() {
        return 3;
    }
}
