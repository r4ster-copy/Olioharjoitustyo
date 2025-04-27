package com.example.harjoitustyo.lutemons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.harjoitustyo.R;
import java.util.ArrayList;

// RecyclerView adapter that displays Lutemon information in a list view
public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {

    private ArrayList<Lutemon> lutemons;

    // Constructor
    // Initializes the adapter with a list of Lutemons to be displayed in the RecyclerView
    public LutemonAdapter(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    // ViewHolder class
    // Holds references to the view elements (image, texts) of a single card
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, typeIcon;
        TextView name, attack, defence, life, experience, trainingPoints, trainingDays, totalFights, fightsWon, location;

        // Creates references to the view elements
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.LutemonImage);
            name = itemView.findViewById(R.id.LutemonListName);
            attack = itemView.findViewById(R.id.LutemonListAttack);
            defence = itemView.findViewById(R.id.LutemonListDefence);
            life = itemView.findViewById(R.id.LutemonListLife);
            experience = itemView.findViewById(R.id.LutemonListExperience);
            typeIcon = itemView.findViewById(R.id.LutemonTypeIcon);
            trainingPoints = itemView.findViewById(R.id.LutemonListTrainingPoints);
            trainingDays = itemView.findViewById(R.id.LutemonListTrainingDays);
            totalFights = itemView.findViewById(R.id.LutemonListTotalFights);
            fightsWon = itemView.findViewById(R.id.LutemonListFightsWon);
            location = itemView.findViewById(R.id.LutemonListLocation);
        }
    }

    // Creates a new ViewHolder for the given view
    @NonNull
    @Override
    public LutemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_list_view_holder, parent, false);
        return new ViewHolder(view);
    }

    // Populates the view with Lutemon data at the given position
    @Override
    public void onBindViewHolder(@NonNull LutemonAdapter.ViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);

        holder.image.setImageResource(l.getImage());
        holder.name.setText(l.getName() + " (" + l.getColor() + ")");
        holder.attack.setText("Attack: " + l.getAttack());
        holder.defence.setText("Defence: " + l.getDefense());
        holder.life.setText("HP: " + l.getCurrentHealth() + "/" + l.getMaxHealth());
        holder.experience.setText("XP: " + l.getExperience());
        holder.trainingPoints.setText("Training Points: " + l.getTrainingPoints());
        holder.trainingDays.setText("Training Days: " + l.getTrainingDays());
        holder.totalFights.setText("Total Fights: " + l.getBattlesFought());
        holder.fightsWon.setText("Fights Won: " + l.getBattlesWon());

        // Displays the Lutemon's current location in the game
        String displayLocation;
        switch (l.getLocation()) {
            case "home":
                displayLocation = "Home";
                break;
            case "training":
                displayLocation = "Training Center";
                break;
            case "fight":
                displayLocation = "Fight Arena";
                break;
            default:
                displayLocation = "Unknown";
                break;
        }
        holder.location.setText("Location: " + displayLocation);

        // Sets the icon corresponding to the Lutemon's type
        switch (l.getType()) {
            case "fire":
                holder.typeIcon.setImageResource(R.drawable.fire_type);
                break;
            case "grass":
                holder.typeIcon.setImageResource(R.drawable.grass_type);
                break;
            case "fairy":
                holder.typeIcon.setImageResource(R.drawable.fairy_type);
                break;
            case "shadow":
                holder.typeIcon.setImageResource(R.drawable.shadow_type);
                break;
            case "normal":
                holder.typeIcon.setImageResource(R.drawable.normal_type);
                break;
            default:
                holder.typeIcon.setImageResource(R.drawable.stock_lutemon);
                break;
        }
    }

    // Returns the size of the list (number of Lutemons)
    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}