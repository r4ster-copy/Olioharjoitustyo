package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.harjoitustyo.move_fragments.MoveLutemonActivityTabAdapter;
import com.google.android.material.tabs.TabLayout;

// Aktiviteetti, jossa käyttäjä voi siirtää Lutemoneja eri sijaintien välillä (Home, Training Center, Fight Arena)
public class MoveLutemonActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    MoveLutemonActivityTabAdapter adapter;

    // Määrittää TabLayoutin ja ViewPagerin logiikan sekä siirtymisen takaisin päävalikkoon
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemon);

        // Takaisin päävalikkoon
        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MoveLutemonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        adapter = new MoveLutemonActivityTabAdapter(this);
        viewPager.setAdapter(adapter);

        // Aseta välilehdet: Home, Training Center ja Fight Arena
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Training Center"));
        tabLayout.addTab(tabLayout.newTab().setText("Fight Arena"));

        // Välilehden valinta vaihtaa näkymän
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Välilehdet seuraavat myös swipe-liikkeitä
        // Vaihdetaan myös taustaväri fragmentin mukaan, kun käyttäjä vaihtaa näkymää
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

                // Haetaan pääasiallinen layout
                View rootLayout = findViewById(R.id.main); // Muista että activity_move_lutemon.xml:ssa pitää olla android:id="@+id/main"

                int backgroundColor;
                switch (position) {
                    case 0:
                        backgroundColor = getResources().getColor(R.color.fragment_home_color, null);
                        break;
                    case 1:
                        backgroundColor = getResources().getColor(R.color.fragment_training_color, null);
                        break;
                    case 2:
                        backgroundColor = getResources().getColor(R.color.fragment_fight_color, null);
                        break;
                    default:
                        backgroundColor = getResources().getColor(R.color.default_background, null);
                }

                rootLayout.setBackgroundColor(backgroundColor);
            }
        });
    }
}