package com.example.harjoitustyo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.harjoitustyo.lutemons.Lutemon;
import java.util.ArrayList;
import java.util.HashSet;

// Adapter used in a RecyclerView to select two Lutemons for battle
public class FightSelectionAdapter extends RecyclerView.Adapter<FightSelectionAdapter.ViewHolder> {

    // Interface to notify about selections
    public interface OnLutemonSelectedListener {
        void onLutemonSelected(Lutemon lutemon, boolean selected);
    }

    private ArrayList<Lutemon> lutemons;              // List of Lutemons to display
    private HashSet<Lutemon> selected = new HashSet<>();  // Selected Lutemons
    private OnLutemonSelectedListener listener;       // Listener for selections
    private boolean selectionLimitReached = false;    // Prevents selecting more than two

    // Creates the adapter with a given Lutemon list and listener
    public FightSelectionAdapter(ArrayList<Lutemon> lutemons, OnLutemonSelectedListener listener) {
        this.lutemons = lutemons;
        this.listener = listener;
    }

    // Sets whether the selection limit is reached
    public void setSelectionLimitReached(boolean reached) {
        this.selectionLimitReached = reached;
        notifyDataSetChanged();
    }

    // Creates a new view for each Lutemon
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_view_holder, parent, false);
        return new ViewHolder(view);
    }

    // Fills the ViewHolder with data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);

        holder.image.setImageResource(l.getImage());
        holder.typeIcon.setImageResource(getTypeIcon(l.getType()));
        holder.name.setText(l.getName() + " (" + l.getColor() + ")");
        holder.attack.setText("Attack: " + l.getAttack());
        holder.defense.setText("Defence: " + l.getDefense());
        holder.health.setText("HP: " + l.getCurrentHealth() + "/" + l.getMaxHealth());
        holder.experience.setText("XP: " + l.getExperience());

        // Highlight selected Lutemons
        if (selected.contains(l)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#C3B1E1"));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        // Handle click selection, max 2 at a time
        holder.itemView.setOnClickListener(v -> {
            if (selected.contains(l)) {
                selected.remove(l);
                listener.onLutemonSelected(l, false);
            } else if (selected.size() < 2) {
                selected.add(l);
                listener.onLutemonSelected(l, true);
            }
            notifyDataSetChanged();
        });

        // Disable selection if the limit is reached
        holder.itemView.setEnabled(!selectionLimitReached || selected.contains(l));
    }

    // Returns the correct type icon ID
    private int getTypeIcon(String type) {
        switch (type) {
            case "fire": return R.drawable.fire_type;
            case "grass": return R.drawable.grass_type;
            case "fairy": return R.drawable.fairy_type;
            case "shadow": return R.drawable.shadow_type;
            case "normal": return R.drawable.normal_type;
            default: return R.drawable.stock_lutemon;
        }
    }

    // Returns the size of the list
    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    // Internal ViewHolder class referring to the view components
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, typeIcon;
        TextView name, attack, defense, health, experience;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.LutemonImage);
            typeIcon = itemView.findViewById(R.id.LutemonTypeIcon);
            name = itemView.findViewById(R.id.LutemonListName);
            attack = itemView.findViewById(R.id.LutemonListAttack);
            defense = itemView.findViewById(R.id.LutemonListDefence);
            health = itemView.findViewById(R.id.LutemonListLife);
            experience = itemView.findViewById(R.id.LutemonListExperience);
        }
    }
}