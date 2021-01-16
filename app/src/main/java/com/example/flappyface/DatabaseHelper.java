package com.example.flappyface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbName = "leaderboardDB";
    private static final String table_name = "leaderboard";
    private static final String table_id = "playerName";
    private static final String table_score = "playerScore";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + table_name + "("
                + table_id + " TEXT PRIMARY KEY, "
                + table_score + " TEXT" +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db); // Create tables again
    }

    public void addPlayer(LeaderboardEntry leaderboardEntry) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(table_id, leaderboardEntry.getPlayerName());
        values.put(table_score, leaderboardEntry.getScore());

        database.insert(table_name, null, values);
        database.close();
    }

    public void updatePlayer(LeaderboardEntry leaderboardEntry) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(table_id, leaderboardEntry.getPlayerName());
        values.put(table_score, leaderboardEntry.getScore());

        database.update(table_name, values, table_id + " = ?", new String[] {leaderboardEntry.getPlayerName()});
        database.close();
    }

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(table_name, null, null);
        database.close();
    }

    public List<LeaderboardEntry> getAllEntries() {
        List<LeaderboardEntry> allEntries = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String selectAllQuery = "SELECT * FROM " + table_name + " ORDER BY CAST(" + table_score + " AS UNSIGNED) DESC";
        Cursor resultSet = database.rawQuery(selectAllQuery, null);

        if(resultSet.moveToFirst()) {
            do {
                String playerName = resultSet.getString(0);
                int score = resultSet.getInt(1);
                LeaderboardEntry leaderboardEntry = new LeaderboardEntry(playerName, score);

                allEntries.add(leaderboardEntry);
            } while(resultSet.moveToNext());
        }

        resultSet.close();
        database.close();

        return allEntries;
    }
}
