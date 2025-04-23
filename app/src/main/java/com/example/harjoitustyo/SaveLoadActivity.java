package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonStorage;

import java.io.*;
import java.util.ArrayList;

public class SaveLoadActivity extends AppCompatActivity {

    private EditText fileNameInput;
    private TextView saveLoadInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_load);

        fileNameInput = findViewById(R.id.FileNameInput);
        saveLoadInfoText = findViewById(R.id.SaveLoadInfoText);

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
            saveLoadInfoText.setText("Please enter a name for the save file.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(filename, Context.MODE_PRIVATE))) {
            oos.writeObject(LutemonStorage.getInstance().getLutemons());
            saveLoadInfoText.setText("Game saved to file: " + filename);
        } catch (IOException e) {
            saveLoadInfoText.setText("Failed to save the game.");
            e.printStackTrace();
        }
    }

    private void loadGame() {
        String filename = fileNameInput.getText().toString().trim();
        if (filename.isEmpty()) {
            saveLoadInfoText.setText("Please enter a name for the file to load.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(openFileInput(filename))) {
            ArrayList<Lutemon> loadedLutemons = (ArrayList<Lutemon>) ois.readObject();
            LutemonStorage.getInstance().clearAll();
            for (Lutemon l : loadedLutemons) {
                LutemonStorage.getInstance().addLutemon(l);
            }
            saveLoadInfoText.setText("Game loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            saveLoadInfoText.setText("Failed to load the game.");
            e.printStackTrace();
        }
    }
}