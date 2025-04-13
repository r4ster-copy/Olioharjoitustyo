package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.example.harjoitustyo.lutemons.LutemonAdapter;
import com.example.harjoitustyo.lutemons.LutemonStorage;

import java.util.ArrayList;

public class ListLutemonActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LutemonAdapter adapter;
    private ArrayList<Lutemon> lutemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemon);

        ImageButton homeButton = findViewById(R.id.BackButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListLutemonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Haetaan Lutemonit ja asetetaan RecyclerView
        lutemons = LutemonStorage.getInstance().getLutemons();

        recyclerView = findViewById(R.id.LutemonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonAdapter(lutemons);
        recyclerView.setAdapter(adapter);
    }
}
