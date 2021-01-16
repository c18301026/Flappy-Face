package com.example.flappyface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeActivity(View view) {
        // User taps play
        if(view.getId() == R.id.playButton) {
            Intent gameIntent = new Intent(this, GameActivity.class);
            gameIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(gameIntent);
        }
        // User taps leaderboard
        if(view.getId() == R.id.boardButton) {
            Intent boardIntent = new Intent(this, LeaderboardActivity.class);
            boardIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(boardIntent);
        }
        // User taps options
        if(view.getId() == R.id.optionsButton) {
            Intent optionIntent = new Intent(this, OptionsActivity.class);
            startActivity(optionIntent);
        }
    }
}