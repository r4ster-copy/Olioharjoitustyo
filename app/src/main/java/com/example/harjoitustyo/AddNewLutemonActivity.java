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

// Aktiviteetti, jossa käyttäjä voi luoda uuden Lutemonin valitsemalla nimen ja värin
public class AddNewLutemonActivity extends AppCompatActivity {

    private EditText nameInput;
    private RadioGroup colorGroup;

    // Alustaa näkymän ja painikkeiden toiminnallisuudet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);

        nameInput = findViewById(R.id.LutemonName);
        colorGroup = findViewById(R.id.ColorGroup);

        // Vie takaisin päävalikkoon
        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddNewLutemonActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Luo uusi Lutemon ja lisää se tallennukseen
        ImageButton addButton = findViewById(R.id.AddNewLutemonButton);
        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            int selectedId = colorGroup.getCheckedRadioButtonId();

            // Estetään tyhjä syöte tai valitsematon väri
            if (name.isEmpty() || selectedId == -1) {
                Toast.makeText(this, "Anna nimi ja valitse väri", Toast.LENGTH_SHORT).show();
                return;
            }

            Lutemon newLutemon = null;

            // Luo oikeantyyppinen Lutemon valinnan perusteella
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

            // Lisää tallennukseen ja tyhjentää kentät
            if (newLutemon != null) {
                LutemonStorage.getInstance().addLutemon(newLutemon);
                Toast.makeText(this, name + " luotiin!", Toast.LENGTH_SHORT).show();
                nameInput.setText("");
                colorGroup.clearCheck();
            }
        });
    }
}