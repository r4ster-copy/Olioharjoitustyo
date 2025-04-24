package com.example.harjoitustyo.fight_fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.harjoitustyo.R;
import com.example.harjoitustyo.lutemons.Lutemon;

// Näyttää ennen taistelua molempien Lutemonien nimet, kuvat ja statukset
public class FragmentIntro extends Fragment {

    private Lutemon lutemon1, lutemon2;

    public FragmentIntro() {
        // Vaadittu oletuskonstruktori fragmentille
    }

    // Luo uusi instanssi intro-fragmentista
    public static FragmentIntro newInstance() {
        return new FragmentIntro();
    }

    // Luo näkymän fragmentille ja näyttää kahden taistelijan tiedot ennen taistelun alkua
    // inflater: näkymän asettelun täyttämiseen
    // container: fragmentin sijoituspaikka
    // savedInstanceState: mahdollinen tallennettu tila
    // palauttaa: käyttöliittymänäkymän
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        // Haetaan taistelijat singletonista
        lutemon1 = FightArenaData.getInstance().getFighter1();
        lutemon2 = FightArenaData.getInstance().getFighter2();

        if (lutemon1 == null || lutemon2 == null) return view;

        // Täytetään ensimmäisen Lutemonin tiedot näkymään
        TextView name1 = view.findViewById(R.id.Lutemon1Name);
        ImageView image1 = view.findViewById(R.id.Lutemon1Image);
        TextView stats1 = view.findViewById(R.id.Lutemon1Stats);

        name1.setText(lutemon1.getName());
        image1.setImageResource(lutemon1.getImage());
        stats1.setText("ATK: " + lutemon1.getAttack() +
                "\nDEF: " + lutemon1.getDefense() +
                "\nHP: " + lutemon1.getCurrentHealth() + "/" + lutemon1.getMaxHealth() +
                "\nXP: " + lutemon1.getExperience());

        // Toisen Lutemonin tiedot
        TextView name2 = view.findViewById(R.id.Lutemon2Name);
        ImageView image2 = view.findViewById(R.id.Lutemon2Image);
        TextView stats2 = view.findViewById(R.id.Lutemon2Stats);

        name2.setText(lutemon2.getName());
        image2.setImageResource(lutemon2.getImage());
        stats2.setText("ATK: " + lutemon2.getAttack() +
                "\nDEF: " + lutemon2.getDefense() +
                "\nHP: " + lutemon2.getCurrentHealth() + "/" + lutemon2.getMaxHealth() +
                "\nXP: " + lutemon2.getExperience());

        // Siirtyy taisteluareena-näkymään viiveellä
        new Handler().postDelayed(() -> {
            ViewPager2 viewPager = requireActivity().findViewById(R.id.viewPager);
            viewPager.setCurrentItem(1, true);
        }, 5000);

        return view;
    }
}