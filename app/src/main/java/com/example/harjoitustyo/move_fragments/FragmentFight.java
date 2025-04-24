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

// Fragmentti, joka näyttää "fight"-sijainnissa olevat Lutemonit ja mahdollistaa niiden siirron Home- tai Training-tilaan
public class FragmentFight extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapterSelectable adapter;

    // Luo ja palauttaa fragmentin näkymän
    // inflater: layoutin täyttäjä
    // container: fragmentin vanhempi näkymä
    // savedInstanceState: mahdollisesti aiempi tila
    // palauttaa: luotu View-olio
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

        // Siirtää valitut Lutemonit Homeen
        toHome.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("home");
            }
            updateList();
        });

        // Siirtää valitut Lutemonit Trainingiin
        toTraining.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("training");
            }
            updateList();
        });

        return view;
    }

    // Päivittää listan, kun fragmentti näkyy uudelleen
    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    // Suodattaa Lutemonit, joiden sijainti on "fight", ja asettaa ne adapteriin
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