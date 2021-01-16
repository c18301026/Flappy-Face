package com.example.flappyface;

public class LeaderboardEntry {
    private String playerName; // this will be the primary key
    private int score;

    public LeaderboardEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score: " + score + ", Player: " + playerName;
    }
}
