package com.example.harjoitustyo.fight_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.harjoitustyo.MainActivity;
import com.example.harjoitustyo.R;
import com.example.harjoitustyo.lutemons.Lutemon;

// Näyttää taistelun voittajan tiedot ja tarjoaa napin paluuseen päävalikkoon
public class FragmentPrintInfo extends Fragment {

    private TextView winnerName, winnerStats, resultText, winnerHistory;
    private ImageView winnerImage;
    private ImageButton backButton;

    public FragmentPrintInfo() {
        super(R.layout.fragment_print_info); // Määrittää käytettävän layoutin
    }

    // Alustaa näkymän komponentit ja palaa päävalikkoon napista
    // view: fragmentin juuri-näkymä
    // savedInstanceState: mahdollinen aiemmin tallennettu tila
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        winnerName = view.findViewById(R.id.WinnerName);
        winnerStats = view.findViewById(R.id.WinnerStats);
        winnerHistory = view.findViewById(R.id.WinnerHistory);
        resultText = view.findViewById(R.id.ResultText);
        winnerImage = view.findViewById(R.id.WinnerImage);
        backButton = view.findViewById(R.id.ButtonBackToMain);

        // Vie käyttäjän takaisin päävalikkoon
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
        });
    }

    // Päivittää voittajan tiedot aina kun fragmentti näkyy
    @Override
    public void onResume() {
        super.onResume();
        updateWinnerInfo();
    }

    // Hakee voittajan ja näyttää tämän tiedot
    private void updateWinnerInfo() {
        Lutemon winner = FightArenaData.getInstance().getWinner();

        if (winner != null) {
            winnerName.setText(winner.getName() + " (" + winner.getColor() + ")");

            String stats = "ATK: " + winner.getAttack() +
                    "\nDEF: " + winner.getDefense() +
                    "\nHP: " + winner.getCurrentHealth() + "/" + winner.getMaxHealth() +
                    "\nXP: " + winner.getExperience();
            winnerStats.setText(stats);

            String history = "Battles: " + winner.getBattlesFought() +
                    "\nWins: " + winner.getBattlesWon() +
                    "\nTraining Days: " + winner.getTrainingDays();
            winnerHistory.setText(history);

            resultText.setText(winner.getName() + " wins the battle!");
            winnerImage.setImageResource(winner.getImage());
        }
    }
}