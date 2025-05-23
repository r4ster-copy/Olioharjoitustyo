package com.example.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.harjoitustyo.lutemons.TypeAdvantageManager;

// Displays each type's strengths and weaknesses visually in a RecyclerView
public class TypeInfoAdapter extends RecyclerView.Adapter<TypeInfoAdapter.ViewHolder> {

    private final String[] types = {"fire", "grass", "fairy", "shadow", "normal"};
    private final LayoutInflater inflater;
    private final Context context;

    // Creates the adapter for a given context
    public TypeInfoAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    // Holds references to one card's components
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView typeIcon;
        TextView typeName;
        ImageView strongIcon1, strongIcon2, weakIcon1, weakIcon2;
        TextView strongName1, strongName2, weakName1, weakName2;
        TextView trainingPreference;

        // Initializes the views in the card
        public ViewHolder(View view) {
            super(view);
            typeIcon = view.findViewById(R.id.TypeIcon);
            typeName = view.findViewById(R.id.TypeName);

            strongIcon1 = view.findViewById(R.id.StrongIcon1);
            strongName1 = view.findViewById(R.id.StrongText1);
            strongIcon2 = view.findViewById(R.id.StrongIcon2);
            strongName2 = view.findViewById(R.id.StrongText2);

            weakIcon1 = view.findViewById(R.id.WeakIcon1);
            weakName1 = view.findViewById(R.id.WeakText1);
            weakIcon2 = view.findViewById(R.id.WeakIcon2);
            weakName2 = view.findViewById(R.id.WeakText2);

            trainingPreference = view.findViewById(R.id.TrainingPreference);
        }
    }

    // Creates a new ViewHolder for the list
    @NonNull
    @Override
    public TypeInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.type_view_holder, parent, false);
        return new ViewHolder(view);
    }

    // Sets the values for the card based on the type
    @Override
    public void onBindViewHolder(@NonNull TypeInfoAdapter.ViewHolder holder, int position) {
        String type = types[position];

        int iconId = context.getResources().getIdentifier(type + "_type", "drawable", context.getPackageName());
        holder.typeIcon.setImageResource(iconId);
        holder.typeName.setText(capitalize(type));

        String[] strong = TypeAdvantageManager.getStrengths(type);
        String[] weak = TypeAdvantageManager.getWeaknesses(type);

        if (strong.length == 2) {
            holder.strongIcon1.setImageResource(getIcon(strong[0]));
            holder.strongName1.setText(capitalize(strong[0]));
            holder.strongIcon2.setImageResource(getIcon(strong[1]));
            holder.strongName2.setText(capitalize(strong[1]));
        }

        if (weak.length == 2) {
            holder.weakIcon1.setImageResource(getIcon(weak[0]));
            holder.weakName1.setText(capitalize(weak[0]));
            holder.weakIcon2.setImageResource(getIcon(weak[1]));
            holder.weakName2.setText(capitalize(weak[1]));
        }

        switch (type) {
            case "fire":
                holder.trainingPreference.setText("Prefers hot weather (+20°C) for training");
                break;
            case "grass":
                holder.trainingPreference.setText("Prefers rainy weather for training");
                break;
            case "fairy":
                holder.trainingPreference.setText("Prefers clear weather for training");
                break;
            case "shadow":
                holder.trainingPreference.setText("Prefers cloudy weather for training");
                break;
            case "normal":
                holder.trainingPreference.setText("Prefers cool weather (0–10°C) for training");
                break;
            default:
                holder.trainingPreference.setText("");
                break;
        }
    }

    // Returns the resource ID for the icon of the given type
    private int getIcon(String typeName) {
        return context.getResources().getIdentifier(typeName + "_type", "drawable", context.getPackageName());
    }

    // Returns the given string capitalized
    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    // Returns the number of types
    @Override
    public int getItemCount() {
        return types.length;
    }
}