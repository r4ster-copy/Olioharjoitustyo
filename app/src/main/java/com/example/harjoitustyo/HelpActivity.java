package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Displays instructions and type advantage information using a RecyclerView
public class HelpActivity extends AppCompatActivity {

    // Creates the view and sets event listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Back button, navigates back to the main menu
        ImageButton backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // RecyclerView for type cards
        RecyclerView typeList = findViewById(R.id.TypeList);
        typeList.setLayoutManager(new LinearLayoutManager(this));
        typeList.setAdapter(new TypeInfoAdapter(this));
    }
}