package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.harjoitustyo.move_fragments.MoveLutemonActivityTabAdapter;
import com.google.android.material.tabs.TabLayout;

// Activity where the user can move Lutemons between different locations (Home, Training Center, Fight Arena)
public class MoveLutemonActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    MoveLutemonActivityTabAdapter adapter;

    // Sets up the TabLayout and ViewPager logic and handles navigation back to the main menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemon);

        // Back button to return to the main menu
        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MoveLutemonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        adapter = new MoveLutemonActivityTabAdapter(this);
        viewPager.setAdapter(adapter);

        // Set up the tabs: Home, Training Center, and Fight Arena
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Training Center"));
        tabLayout.addTab(tabLayout.newTab().setText("Fight Arena"));

        // Selecting a tab changes the current view
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Tabs also follow swipe gestures
        // Changes the background color depending on the selected fragment
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

                // Get the main layout
                View rootLayout = findViewById(R.id.main); // Make sure your activity_move_lutemon.xml has android:id="@+id/main"

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