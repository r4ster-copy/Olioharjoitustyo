package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonBlack;
import com.example.harjoitustyo.lutemons.LutemonGreen;
import com.example.harjoitustyo.lutemons.LutemonOrange;
import com.example.harjoitustyo.lutemons.LutemonPink;
import com.example.harjoitustyo.lutemons.LutemonWhite;
import com.example.harjoitustyo.lutemons.LutemonStorage;

// Activity where the user can create a new Lutemon by selecting a name and color
public class AddNewLutemonActivity extends AppCompatActivity {

    private EditText nameInput;
    private RadioGroup colorGroup;

    // Initializes the view and button functionalities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);

        nameInput = findViewById(R.id.LutemonName);
        colorGroup = findViewById(R.id.ColorGroup);

        // Navigates back to the main menu
        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddNewLutemonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Creates a new Lutemon and adds it to storage
        ImageButton addButton = findViewById(R.id.AddNewLutemonButton);
        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            int selectedId = colorGroup.getCheckedRadioButtonId();

            // Prevents empty name input or no color selection
            if (name.isEmpty() || selectedId == -1) {
                Toast.makeText(this, "Please provide a name and select a color", Toast.LENGTH_SHORT).show();
                return;
            }

            Lutemon newLutemon = null;

            // Creates the correct Lutemon type based on selection
            if (selectedId == R.id.WhiteLutemon)
                newLutemon = new LutemonWhite(name);
            else if (selectedId == R.id.GreenLutemon)
                newLutemon = new LutemonGreen(name);
            else if (selectedId == R.id.PinkLutemon)
                newLutemon = new LutemonPink(name);
            else if (selectedId == R.id.OrangeLutemon)
                newLutemon = new LutemonOrange(name);
            else if (selectedId == R.id.BlackLutemon)
                newLutemon = new LutemonBlack(name);

            // Adds the Lutemon to storage and clears the input fields
            if (newLutemon != null) {
                LutemonStorage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, name + " created!", Toast.LENGTH_SHORT).show();
                nameInput.setText("");
                colorGroup.clearCheck();
            }
        });
    }
}