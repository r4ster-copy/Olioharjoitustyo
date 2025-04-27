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

// Fragment that displays Lutemons located in Home and allows moving them to Training or Fight
public class FragmentHome extends Fragment {

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

        View view = inflater.inflate(R.layout.fragment_move_lutemon_home, container, false);

        recyclerView = view.findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Lutemon> homeLutemons = (ArrayList<Lutemon>) LutemonStorage.getInstance()
                .getLutemons()
                .stream()
                .filter(l -> l.getLocation().equals("home"))
                .collect(Collectors.toList());

        adapter = new LutemonAdapterSelectable(homeLutemons);
        recyclerView.setAdapter(adapter);

        Button moveToTraining = view.findViewById(R.id.moveToTrainingButton);
        Button moveToFight = view.findViewById(R.id.moveToFightButton);

        // Moves selected Lutemons to Training
        moveToTraining.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("training");
            }
            updateList();
        });

        // Moves selected Lutemons to Fight
        moveToFight.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("fight");
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

    // Filters Lutemons located in "home" and updates the adapter list
    private void updateList() {
        adapter.updateList(LutemonStorage.getInstance().getLutemons()
                .stream()
                .filter(l -> l.getLocation().equals("home"))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}