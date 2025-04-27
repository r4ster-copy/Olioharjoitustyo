package com.example.harjoitustyo.fight_fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.harjoitustyo.R;
import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.TypeAdvantageManager;

// Handles the battle view's logic and visual representation
public class FragmentFightArena extends Fragment {

    private Lutemon attacker;
    private Lutemon defender;
    private ImageView attackerImage, defenderImage;
    private TextView attackerName, defenderName;
    private TextView attackerStats, defenderStats;
    private TextView attackerStatus, defenderStatus, effectStatus, criticalStatus;
    private ImageButton fightButton;

    // Initializes the view and connects UI components to variables
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fight_arena, container, false);

        attackerImage = view.findViewById(R.id.AttackerImage);
        defenderImage = view.findViewById(R.id.DefenderImage);
        attackerName = view.findViewById(R.id.AttackerName);
        defenderName = view.findViewById(R.id.DefenderName);
        attackerStats = view.findViewById(R.id.AttackerStats);
        defenderStats = view.findViewById(R.id.DefenderStats);
        attackerStatus = view.findViewById(R.id.AttackerStatus);
        defenderStatus = view.findViewById(R.id.DefenderStatus);
        fightButton = view.findViewById(R.id.FightButton);
        effectStatus = view.findViewById(R.id.EffectStatus);
        criticalStatus = view.findViewById(R.id.CriticalStatus);
        attacker = FightArenaData.getInstance().getFighter1();
        defender = FightArenaData.getInstance().getFighter2();

        updateUI();

        // Starts the attack function when the button is pressed
        fightButton.setOnClickListener(v -> performAttack());

        return view;
    }

    // Handles the attack and related logic
    private void performAttack() {
        fightButton.setEnabled(false);

        setLutemonImage(attackerImage, attacker.getColor(), "attack");
        attackerStatus.setText(attacker.getName() + " attacks!");
        defenderStatus.setText("");
        effectStatus.setText("");
        criticalStatus.setText("");

        new Handler().postDelayed(() -> {
            setLutemonImage(defenderImage, defender.getColor(), "hitmark");

            int totalAttack = attacker.getEffectiveAttack();
            int defenderDefense = defender.getEffectiveDefense();
            int baseDamage = Math.max(1, totalAttack - defenderDefense);

            // Determine type advantage and apply multiplier
            double multiplier = TypeAdvantageManager.getTypeMultiplier(attacker.getType(), defender.getType());
            int finalDamage = Math.max(1, (int) Math.round(baseDamage * multiplier));

            // 10% chance for a critical hit
            boolean isCritical = Math.random() < .10;
            if (isCritical) {
                finalDamage *= 2;
                criticalStatus.setText("Critical hit!");
            }

            // Apply the final damage and display status
            defender.takeDamage(finalDamage);
            defenderStatus.setText(defender.getName() + " took " + finalDamage + " damage!");

            if (multiplier == 1.25) {
                effectStatus.setText("It's super effective!");
            } else if (multiplier == 0.75) {
                effectStatus.setText("It's not very effective...");
            } else {
                effectStatus.setText("");
            }

            new Handler().postDelayed(() -> {
                if (!defender.isAlive()) {
                    setLutemonImage(defenderImage, defender.getColor(), "down");
                    defenderStatus.setText(defender.getName() + " is down!");
                    effectStatus.setText("");
                    criticalStatus.setText("");

                    // Post-battle handling when a Lutemon is defeated
                    new Handler().postDelayed(() -> {
                        defender.resetStats();
                        defender.setLocation("home");
                        defender.addFight();
                        attacker.addExperience(1);
                        attacker.addFight();
                        attacker.addWin();
                        attacker.resetHealth();

                        FightArenaData.getInstance().setWinner(attacker);

                        // Switch to the result view
                        requireActivity().runOnUiThread(() -> {
                            ViewPager2 pager = requireActivity().findViewById(R.id.viewPager);
                            pager.setCurrentItem(2);
                        });
                    }, 4000);
                } else {
                    // If both are alive, swap roles
                    swapRoles();
                    updateUI();
                    fightButton.setEnabled(true);
                }
            }, 2000);
        }, 2000);
    }

    // Updates the view with the attacker and defender information
    private void updateUI() {
        setLutemonImage(attackerImage, attacker.getColor(), null);
        setLutemonImage(defenderImage, defender.getColor(), null);

        attackerName.setText(attacker.getName());
        defenderName.setText(defender.getName());

        attackerStats.setText("ATK: " + attacker.getAttack() +
                "  DEF: " + attacker.getDefense() +
                "  HP: " + attacker.getCurrentHealth() + "/" + attacker.getMaxHealth());

        defenderStats.setText("ATK: " + defender.getAttack() +
                "  DEF: " + defender.getDefense() +
                "  HP: " + defender.getCurrentHealth() + "/" + defender.getMaxHealth());

        attackerStatus.setText("");
        defenderStatus.setText("");
        effectStatus.setText("");
        criticalStatus.setText("");
    }

    // Swaps attacker and defender roles
    private void swapRoles() {
        Lutemon temp = attacker;
        attacker = defender;
        defender = temp;
    }

    // Sets the Lutemon's image based on its situation and color
    private void setLutemonImage(ImageView view, String color, String type) {
        String resourceName = "lutemon_" + color.toLowerCase();
        if (type != null) resourceName += "_" + type;
        int resId = getResources().getIdentifier(resourceName, "drawable", requireContext().getPackageName());
        view.setImageResource(resId != 0 ? resId : R.drawable.stock_lutemon);
    }
}