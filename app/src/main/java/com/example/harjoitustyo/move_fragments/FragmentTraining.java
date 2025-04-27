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

// Fragment that displays Lutemons located in the Training Center and allows moving them to other locations
public class FragmentTraining extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapterSelectable adapter;

    // Creates and returns the view for this fragment
    // inflater: component for creating the layout
    // container: parent view of the fragment
    // savedInstanceState: saved state
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_move_lutemon_training, container, false);

        recyclerView = view.findViewById(R.id.trainingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateList();

        Button toHome = view.findViewById(R.id.moveToHomeButton);
        Button toFight = view.findViewById(R.id.moveToFightButton);

        // Moves selected Lutemons to Home location
        toHome.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("home");
            }
            updateList();
        });

        // Moves selected Lutemons to Fight location
        toFight.setOnClickListener(v -> {
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

    // Updates the visible Lutemon list by filtering those located in "training"
    private void updateList() {
        ArrayList<Lutemon> trainingLutemons = (ArrayList<Lutemon>) LutemonStorage.getInstance()
                .getLutemons()
                .stream()
                .filter(l -> l.getLocation().equals("training"))
                .collect(Collectors.toList());

        adapter = new LutemonAdapterSelectable(trainingLutemons);
        recyclerView.setAdapter(adapter);
    }
}