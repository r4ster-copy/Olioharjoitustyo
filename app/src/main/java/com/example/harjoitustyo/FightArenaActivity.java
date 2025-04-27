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

// Activity that manages the three phases of a battle (intro, fight, result) using ViewPager2
public class FightArenaActivity extends AppCompatActivity {

    // Initializes the view and sets up a ViewPager2 adapter with three fragments
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_arena);

        Lutemon fighter1 = FightArenaData.getInstance().getFighter1();
        Lutemon fighter2 = FightArenaData.getInstance().getFighter2();

        // If no fighters are set, close the activity
        if (fighter1 == null || fighter2 == null) {
            finish();
            return;
        }

        // Sets up a ViewPager with three pages: intro, fight, and result
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0: return new FragmentIntro();      // Introduction
                    case 1: return new FragmentFightArena(); // The actual fight
                    case 2: return new FragmentPrintInfo();  // Result and statistics
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