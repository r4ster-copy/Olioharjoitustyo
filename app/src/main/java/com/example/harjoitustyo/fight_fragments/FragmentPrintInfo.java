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

// Displays the winner's information after the battle and provides a button to return to the main menu
public class FragmentPrintInfo extends Fragment {

    private TextView winnerName, winnerStats, resultText, winnerHistory;
    private ImageView winnerImage;
    private ImageButton backButton;

    public FragmentPrintInfo() {
        super(R.layout.fragment_print_info); // Defines the layout used
    }

    // Initializes the view components and sets up the return to main menu button
    // view: root view of the fragment
    // savedInstanceState: possible previously saved state
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        winnerName = view.findViewById(R.id.WinnerName);
        winnerStats = view.findViewById(R.id.WinnerStats);
        winnerHistory = view.findViewById(R.id.WinnerHistory);
        resultText = view.findViewById(R.id.ResultText);
        winnerImage = view.findViewById(R.id.WinnerImage);
        backButton = view.findViewById(R.id.ButtonBackToMain);

        // Takes the user back to the main menu
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
        });
    }

    // Updates the winner's information whenever the fragment becomes visible
    @Override
    public void onResume() {
        super.onResume();
        updateWinnerInfo();
    }

    // Retrieves and displays the winner's information
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