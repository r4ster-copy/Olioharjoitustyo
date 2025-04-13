package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.harjoitustyo.move_fragments.MoveLutemonActivityTabAdapter;
import com.google.android.material.tabs.TabLayout;

public class MoveLutemonActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    MoveLutemonActivityTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemon);

        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MoveLutemonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        adapter = new MoveLutemonActivityTabAdapter(this);
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Training Center"));
        tabLayout.addTab(tabLayout.newTab().setText("Fight Arena"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}
