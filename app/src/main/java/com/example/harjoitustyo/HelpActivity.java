package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageButton backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, MainActivity.class);
            startActivity(intent);
        });

        RecyclerView typeList = findViewById(R.id.TypeList);
        typeList.setLayoutManager(new LinearLayoutManager(this));
        typeList.setAdapter(new TypeInfoAdapter(this));
    }
}
