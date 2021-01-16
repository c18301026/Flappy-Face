package com.example.flappyface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LeaderboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ListView leaderboard = findViewById(R.id.leaderboard);

        DatabaseHelper databaseHelper = new DatabaseHelper(LeaderboardActivity.this);
        ArrayAdapter arrayAdapter = new ArrayAdapter<LeaderboardEntry>(LeaderboardActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getAllEntries());
        leaderboard.setAdapter(arrayAdapter);
    }

    public void backToMenu(View view) {
        // User taps play
        if(view.getId() == R.id.menuButton) {
            Intent menuIntent = new Intent(this, MainActivity.class);
            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(menuIntent);
        }
    }
}