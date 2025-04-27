package com.example.harjoitustyo.move_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harjoitustyo.R;
import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonAdapterSelectable;
import com.example.harjoitustyo.lutemons.LutemonStorage;

import java.util.ArrayList;
import java.util.stream.Collectors;

// Fragment that displays Lutemons located in "fight" and allows moving them to Home or Training
public class FragmentFight extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapterSelectable adapter;

    // Creates and returns the fragment's view
    // inflater: layout inflater
    // container: parent view of the fragment
    // savedInstanceState: possibly previously saved state
    // returns: the created View object
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_move_lutemon_fight, container, false);

        recyclerView = view.findViewById(R.id.fightRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateList();

        Button toHome = view.findViewById(R.id.moveToHomeButton);
        Button toTraining = view.findViewById(R.id.moveToTrainingButton);

        // Moves selected Lutemons to Home
        toHome.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("home");
            }
            updateList();
        });

        // Moves selected Lutemons to Training
        toTraining.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("training");
            }
            updateList();
        });

        return view;
    }

    // Updates the list when the fragment becomes visible again
    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    // Filters Lutemons located in "fight" and sets them to the adapter
    private void updateList() {
        ArrayList<Lutemon> fightLutemons = (ArrayList<Lutemon>) LutemonStorage.getInstance()
                .getLutemons()
                .stream()
                .filter(l -> l.getLocation().equals("fight"))
                .collect(Collectors.toList());

        adapter = new LutemonAdapterSelectable(fightLutemons);
        recyclerView.setAdapter(adapter);
    }
}