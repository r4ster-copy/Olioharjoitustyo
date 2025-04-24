package com.example.harjoitustyo.move_fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// Adapteri ViewPager2:lle, joka vaihtaa fragmentit Home, Training ja Fight välillä
public class MoveLutemonActivityTabAdapter extends FragmentStateAdapter {

    // Luo adapterin ja liittää sen FragmentActivityyn
    // fa: aktiivinen fragmentti-aktiviteetti
    public MoveLutemonActivityTabAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    // Palauttaa halutun fragmentin positionin mukaan
    // position: haluttu sivu
    // palauttaa: Fragment-olio (Home, Training tai Fight)
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0: return new FragmentHome();
            case 1: return new FragmentTraining();
            case 2: return new FragmentFight();
            default: return new FragmentHome(); // varmuuden vuoksi
        }
    }

    // Palauttaa fragmenttien kokonaismäärän
    // palauttaa: 3 (Home, Training, Fight)
    @Override
    public int getItemCount() {
        return 3;
    }
}