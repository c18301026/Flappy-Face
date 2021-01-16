package com.example.flappyface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void options(View view) {
        // User taps play music
        if(view.getId() == R.id.bgmButton) {
            Intent bgmIntent = new Intent(this, MusicActivity.class);
            startActivity(bgmIntent);
        }
        // User taps clear leaderboard
        if(view.getId() == R.id.clearButton) {
            DatabaseHelper databaseHelper = new DatabaseHelper(OptionsActivity.this);
            databaseHelper.deleteAll();
            Toast.makeText(OptionsActivity.this, "All players have been deleted", Toast.LENGTH_LONG).show();
        }
        // User taps go back
        if(view.getId() == R.id.backButton) {
            finish();
        }
    }
}