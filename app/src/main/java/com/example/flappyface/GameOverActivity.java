package com.example.flappyface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class GameOverActivity extends AppCompatActivity {
    private TextView scoreDisplay;
    private String score; // From the game activity
    private EditText nameField;
    private DatabaseHelper databaseHelper;
    private boolean nameAlreadyExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        nameAlreadyExists = false;
        scoreDisplay = findViewById(R.id.scoreDisplay);
        nameField = findViewById(R.id.nameField);

        // Get the score from the game activity
        score = getIntent().getExtras().get("score").toString();

        // Set the text for display
        scoreDisplay.setText("Your score is " + score);

        databaseHelper = new DatabaseHelper(GameOverActivity.this);
    }

    public void addPlayerName(View view) {
        if(view.getId() == R.id.addName) {
            List<LeaderboardEntry> allEntries = databaseHelper.getAllEntries();

            // Check all the entries if the name already exists
            for(LeaderboardEntry leaderboardEntry : allEntries) {
                if(leaderboardEntry.getPlayerName().equals(nameField.getText().toString())) {
                    nameAlreadyExists = true;
                    break;
                };
            }

            LeaderboardEntry leaderboardEntry = new LeaderboardEntry(nameField.getText().toString(), Integer.valueOf(score));

            if(nameAlreadyExists) {
                databaseHelper.updatePlayer(leaderboardEntry);
            } else {
                databaseHelper.addPlayer(leaderboardEntry);
            }

            Intent boardIntent = new Intent(this, LeaderboardActivity.class);
            boardIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(boardIntent);
        }
    }
}