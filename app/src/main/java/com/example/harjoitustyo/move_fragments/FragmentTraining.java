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

// Fragmentti, joka näyttää Training Centerissä olevat Lutemonit ja mahdollistaa siirron muihin sijainteihin
public class FragmentTraining extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapterSelectable adapter;

    // Luo ja palauttaa näkymän tälle fragmentille
    // inflater: komponentti layoutin luomiseen
    // container: fragmentin näkymän vanhempi
    // savedInstanceState: tallennettu tila
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

        // Siirtää valitut Lutemonit Home-sijaintiin
        toHome.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("home");
            }
            updateList();
        });

        // Siirtää valitut Lutemonit Fight-sijaintiin
        toFight.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("fight");
            }
            updateList();
        });

        return view;
    }

    // Päivittää listan kun fragmentti näkyy uudelleen
    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    // Päivittää näkyvissä olevan Lutemon-listan suodattamalla ne, joiden sijainti on "training"
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