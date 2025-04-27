package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harjoitustyo.fight_fragments.FightArenaData;
import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonStorage;
import java.util.ArrayList;

// Activity where the user selects two Lutemons to participate in a battle
public class PickYourLutemonActivity extends AppCompatActivity {

    private ArrayList<Lutemon> fightLutemons;
    private ArrayList<Lutemon> selectedLutemons = new ArrayList<>();
    private FightSelectionAdapter adapter;
    private ImageButton fightButton;

    // Opens the selection screen, initializes the list, and navigates to the battle arena once ready
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_your_lutemon);

        // Back button to return to the main menu
        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(PickYourLutemonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Fetch all Lutemons whose location is "fight"
        fightLutemons = new ArrayList<>();
        for (Lutemon l : LutemonStorage.getInstance().getLutemons()) {
            if (l.getLocation().equals("fight")) {
                fightLutemons.add(l);
            }
        }

        // Initialize the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.FightLutemonRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FightSelectionAdapter(fightLutemons, this::onLutemonSelected);
        recyclerView.setAdapter(adapter);

        // Settings for the Fight button
        fightButton = findViewById(R.id.FightButton);
        fightButton.setEnabled(false);
        fightButton.setAlpha(0.4f);
        fightButton.setOnClickListener(v -> {
            FightArenaData.getInstance().setFighters(selectedLutemons.get(0), selectedLutemons.get(1));

            Intent intent = new Intent(PickYourLutemonActivity.this, FightArenaActivity.class);
            startActivity(intent);
        });
    }

    // Handles Lutemon selection logic, prevents selecting more than two
    private void onLutemonSelected(Lutemon lutemon, boolean selected) {
        if (selected) {
            if (selectedLutemons.size() < 2) {
                selectedLutemons.add(lutemon);
            }
        } else {
            selectedLutemons.remove(lutemon);
        }

        // Allow at most two selections
        adapter.setSelectionLimitReached(selectedLutemons.size() >= 2);
        boolean ready = selectedLutemons.size() == 2;
        fightButton.setEnabled(ready);
        fightButton.setAlpha(ready ? 1f : 0.4f);
    }
}