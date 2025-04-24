package com.example.harjoitustyo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.example.harjoitustyo.fight_fragments.FightArenaData;
import com.example.harjoitustyo.fight_fragments.FragmentFightArena;
import com.example.harjoitustyo.fight_fragments.FragmentIntro;
import com.example.harjoitustyo.fight_fragments.FragmentPrintInfo;
import com.example.harjoitustyo.lutemons.Lutemon;

// Aktiviteetti, joka hallitsee taistelun kolmea vaihetta (intro, taistelu, lopputulos) ViewPager2:n avulla
public class FightArenaActivity extends AppCompatActivity {

    // Alustaa näkymän ja asettaa ViewPager2:lle kolmen fragmentin adapterin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_arena);

        Lutemon fighter1 = FightArenaData.getInstance().getFighter1();
        Lutemon fighter2 = FightArenaData.getInstance().getFighter2();

        // Jos taistelijoita ei ole asetettu, suljetaan näkymä
        if (fighter1 == null || fighter2 == null) {
            finish();
            return;
        }

        // Määrittää kolmen sivun ViewPagerin: intro, taistelu ja tulos
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0: return new FragmentIntro();      // Esittely
                    case 1: return new FragmentFightArena(); // Itse taistelu
                    case 2: return new FragmentPrintInfo();  // Tulos ja tilastot
                    default: return new FragmentIntro();
                }
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });
    }
}