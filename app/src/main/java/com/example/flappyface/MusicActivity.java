package com.example.flappyface;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }

    // Reference: The following code is from https://www.youtube.com/watch?v=C_Ka7cKwXW0
    public void options(View view) {
        if(view.getId() == R.id.theme1) {
            stopPlayer();
            if(mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.theme1);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopPlayer();
                    }
                });
            }
            mediaPlayer.start();
        }
        if(view.getId() == R.id.theme2) {
            stopPlayer();
            if(mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.theme2);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopPlayer();
                    }
                });
            }
            mediaPlayer.start();
        }
        if(view.getId() == R.id.theme3) {
            stopPlayer();
            if(mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.theme3);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopPlayer();
                    }
                });
            }
            mediaPlayer.start();
        }
        if(view.getId() == R.id.backButton) {
            finish();
        }
    }

    public void stop(View view) {
        stopPlayer();
    }

    private void stopPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
    // Reference complete
}