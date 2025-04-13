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

public class LutemonAdapterSelectable extends RecyclerView.Adapter<LutemonAdapterSelectable.ViewHolder> {

    private ArrayList<Lutemon> lutemons;
    private ArrayList<Lutemon> selected = new ArrayList<>();

    public LutemonAdapterSelectable(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, typeIcon;
        TextView name, attack, defence, life, experience;
        CheckBox checkBox;

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

    @NonNull
    @Override
    public LutemonAdapterSelectable.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonAdapterSelectable.ViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);

        holder.image.setImageResource(l.getImage());
        holder.name.setText(l.getName() + " (" + l.getColor() + ")");
        holder.attack.setText("Attack: " + l.getAttack());
        holder.defence.setText("Defence: " + l.getDefense());
        holder.life.setText("HP: " + l.getCurrentHealth() + "/" + l.getMaxHealth());
        holder.experience.setText("XP: " + l.getExperience());

        // Type icon
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

        // Selection logic
        holder.itemView.setOnClickListener(v -> {
            if (selected.contains(l)) {
                selected.remove(l);
                holder.itemView.setBackgroundColor(0x00000000);
            } else {
                selected.add(l);
                holder.itemView.setBackgroundColor(Color.parseColor("#673AB7"));
            }
        });

        if (selected.contains(l)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#673AB7"));
        } else {
            holder.itemView.setBackgroundColor(0x00000000);
        }
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public ArrayList<Lutemon> getSelectedLutemons() {
        return selected;
    }

    public void updateList(ArrayList<Lutemon> newList) {
        this.lutemons = newList;
        selected.clear();
        notifyDataSetChanged();
    }
}
