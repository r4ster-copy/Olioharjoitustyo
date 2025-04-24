package com.example.harjoitustyo.lutemons;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.harjoitustyo.R;
import java.util.ArrayList;

// Adapteri Lutemonien listaukseen, jossa käyttäjä voi valita useampia olioita valintaa varten
public class LutemonAdapterSelectable extends RecyclerView.Adapter<LutemonAdapterSelectable.ViewHolder> {

    private ArrayList<Lutemon> lutemons;
    private ArrayList<Lutemon> selected = new ArrayList<>();

    // Konstruktori
    // Alustaa adapterin Lutemon-listalla
    public LutemonAdapterSelectable(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    // ViewHolder pitää viitteet yksittäisen näkymän elementteihin (kuvat, tekstit, checkbox)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, typeIcon;
        TextView name, attack, defence, life, experience;
        CheckBox checkBox;

        // Luo viitteet näkymän elementteihin ja lisää checkbox ohjelmallisesti
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.LutemonImage);
            typeIcon = itemView.findViewById(R.id.LutemonTypeIcon);
            name = itemView.findViewById(R.id.LutemonListName);
            attack = itemView.findViewById(R.id.LutemonListAttack);
            defence = itemView.findViewById(R.id.LutemonListDefence);
            life = itemView.findViewById(R.id.LutemonListLife);
            experience = itemView.findViewById(R.id.LutemonListExperience);
            checkBox = new CheckBox(itemView.getContext());
            ((ViewGroup) itemView).addView(checkBox);
        }
    }

    // Luo uuden korttinäkymän (ViewHolderin)
    @NonNull
    @Override
    public LutemonAdapterSelectable.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_view_holder, parent, false);
        return new ViewHolder(view);
    }

    // Täyttää yksittäisen kortin Lutemonin tiedoilla
    @Override
    public void onBindViewHolder(@NonNull LutemonAdapterSelectable.ViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);

        holder.image.setImageResource(l.getImage());
        holder.name.setText(l.getName() + " (" + l.getColor() + ")");
        holder.attack.setText("Attack: " + l.getAttack());
        holder.defence.setText("Defence: " + l.getDefense());
        holder.life.setText("HP: " + l.getCurrentHealth() + "/" + l.getMaxHealth());
        holder.experience.setText("XP: " + l.getExperience());

        // Asettaa Lutemonin tyyppikuvakkeen
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

        // Lisää/poistaa Lutemonin valittujen joukosta käyttäjän klikkauksen mukaan
        holder.itemView.setOnClickListener(v -> {
            if (selected.contains(l)) {
                selected.remove(l);
                holder.itemView.setBackgroundColor(0x00000000);
            } else {
                selected.add(l);
                holder.itemView.setBackgroundColor(Color.parseColor("#673AB7"));
            }
        });

        // Päivittää valinnan taustavärin mukaan
        if (selected.contains(l)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#673AB7"));
        } else {
            holder.itemView.setBackgroundColor(0x00000000);
        }
    }

    // Palauttaa listan koon
    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    // Palauttaa kaikki valitut Lutemonit
    public ArrayList<Lutemon> getSelectedLutemons() {
        return selected;
    }

    // Korvaa nykyisen listan uudella ja tyhjentää valinnat
    public void updateList(ArrayList<Lutemon> newList) {
        this.lutemons = newList;
        selected.clear();
        notifyDataSetChanged();
    }
}