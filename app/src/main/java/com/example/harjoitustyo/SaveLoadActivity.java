package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonStorage;

import java.io.*;
import java.util.ArrayList;

public class SaveLoadActivity extends AppCompatActivity {

    private EditText fileNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_load); // Luo tämä layout myöhemmin

        fileNameInput = findViewById(R.id.FileNameInput);

        Button saveButton = findViewById(R.id.SaveButton);
        Button loadButton = findViewById(R.id.LoadButton);
        ImageButton backButton = findViewById(R.id.BackButton);

        saveButton.setOnClickListener(v -> saveGame());
        loadButton.setOnClickListener(v -> loadGame());

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void saveGame() {
        String filename = fileNameInput.getText().toString().trim();
        if (filename.isEmpty()) {
            Toast.makeText(this, "Anna tallennustiedoston nimi", Toast.LENGTH_SHORT).show();
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(filename, Context.MODE_PRIVATE))) {
            oos.writeObject(LutemonStorage.getInstance().getLutemons());
            Toast.makeText(this, "Tallennettu tiedostoon: " + filename, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Tallennus epäonnistui", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void loadGame() {
        String filename = fileNameInput.getText().toString().trim();
        if (filename.isEmpty()) {
            Toast.makeText(this, "Anna ladattavan tiedoston nimi", Toast.LENGTH_SHORT).show();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(openFileInput(filename))) {
            ArrayList<Lutemon> loadedLutemons = (ArrayList<Lutemon>) ois.readObject();
            LutemonStorage.getInstance().clearAll();
            for (Lutemon l : loadedLutemons) {
                LutemonStorage.getInstance().addLutemon(l);
            }
            Toast.makeText(this, "Ladattiin tiedostosta: " + filename, Toast.LENGTH_SHORT).show();
        } catch (IOException | ClassNotFoundException e) {
            Toast.makeText(this, "Lataus epäonnistui", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}