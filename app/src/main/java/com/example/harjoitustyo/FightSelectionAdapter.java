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

// Adapteri, jota käytetään RecyclerViewissä valitsemaan kaksi Lutemonia taisteluun
public class FightSelectionAdapter extends RecyclerView.Adapter<FightSelectionAdapter.ViewHolder> {

    // Rajapinta, jolla ilmoitetaan valinnoista
    public interface OnLutemonSelectedListener {
        void onLutemonSelected(Lutemon lutemon, boolean selected);
    }

    private ArrayList<Lutemon> lutemons;              // Näytettävä Lutemon-lista
    private HashSet<Lutemon> selected = new HashSet<>();  // Valitut Lutemonit
    private OnLutemonSelectedListener listener;       // Valintojen kuuntelija
    private boolean selectionLimitReached = false;    // Estää valitsemasta yli kahta

    // Luo adapterin annetulla Lutemon-listalla ja kuuntelijalla
    public FightSelectionAdapter(ArrayList<Lutemon> lutemons, OnLutemonSelectedListener listener) {
        this.lutemons = lutemons;
        this.listener = listener;
    }

    // Asettaa valintarajoituksen päälle tai pois
    public void setSelectionLimitReached(boolean reached) {
        this.selectionLimitReached = reached;
        notifyDataSetChanged();
    }

    // Luo uuden näkymän kullekin Lutemonille
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_view_holder, parent, false);
        return new ViewHolder(view);
    }

    // Täyttää ViewHolderin tiedoilla
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

        // Korostetaan valitut Lutemonit
        if (selected.contains(l)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#C3B1E1"));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        // Klikkivalinta, max 2 kerrallaan
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

        // Poistaa käytöstä jos valintaraja täyttyy
        holder.itemView.setEnabled(!selectionLimitReached || selected.contains(l));
    }

    // Palauttaa oikean tyyppikuvakkeen id:n
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

    // Palauttaa listan koon
    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    // Sisäinen ViewHolder-luokka, joka viittaa näkymän osiin
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