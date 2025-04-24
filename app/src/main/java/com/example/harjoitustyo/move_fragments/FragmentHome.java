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

// Fragmentti, joka näyttää Home-sijainnissa olevat Lutemonit ja mahdollistaa niiden siirron Training- tai Fight-tilaan
public class FragmentHome extends Fragment {

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

        // Siirtää valitut Lutemonit Trainingiin
        moveToTraining.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("training");
            }
            updateList();
        });

        // Siirtää valitut Lutemonit Fight-tilaan
        moveToFight.setOnClickListener(v -> {
            for (Lutemon l : adapter.getSelectedLutemons()) {
                l.setLocation("fight");
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

    // Suodattaa Lutemonit, joiden sijainti on "home", ja päivittää adapterin listan
    private void updateList() {
        adapter.updateList(LutemonStorage.getInstance().getLutemons()
                .stream()
                .filter(l -> l.getLocation().equals("home"))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}