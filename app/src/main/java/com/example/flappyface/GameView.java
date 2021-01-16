package com.example.flappyface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

public class GameView extends View {
    private Player player;
    private Bitmap face;
    private Obstacle column1, column2;
    private int score;
    private Paint scorePaint = new Paint();
    private boolean screenTap = false;
    private WindowManager windowManager;
    private Point size = new Point();
    private int screenWidth, screenHeight;
    private int topBorder = 100;
    private Random columnY = new Random();
    private int columnGap = 550;

    // Constructor method
    public GameView(Context context) {
        super(context);

        // Get the screen width & height
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Create the player's character
        face = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        player = new Player(50, topBorder, face.getWidth(), face.getHeight(), 10, face);

        // Create the obstacles that the player must avoid
        column1 = new Obstacle(screenWidth, topBorder, 175, columnY.nextInt((screenHeight - columnGap) - topBorder) + topBorder, 9);
        column2 = new Obstacle(screenWidth, column1.getHeight() + columnGap, column1.getWidth(), screenHeight, column1.getSpeed());

        // Score properties
        score = 0;
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT);
        scorePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Code relating to player movement & interactions with other objects
        player.gravity();
        if(player.getY() < 100) {
            player.setY(100); // Prevent player from going above the screen
        } else if(player.getY() > (canvas.getHeight() - player.getSprite().getHeight())) {
            if(player.getAlive()) {
                goToGameOverActivity();
                player.setAlive(false);
            }
        }

        // Display the score text
        canvas.drawText("Score: " + score, 20, 60, scorePaint);
        canvas.drawBitmap(player.getSprite(), player.getX(), player.getY(), null);

        // Draw and move the obstacles
        canvas.drawRect(column1.getX(), column1.getY(), column1.getX() + column1.getWidth(), column1.getHeight(), column1.getObstaclePaint());
        canvas.drawRect(column2.getX(), column1.getHeight() + columnGap, column2.getX() + column2.getWidth(), screenHeight, column2.getObstaclePaint());
        column1.move();
        column2.move();

        // Obstacles reach the left side of the screen
        if((column1.getX() + column1.getWidth()) < 0) {
            column1.setX(screenWidth + column1.getWidth());
            column2.setX(screenWidth + column2.getWidth());
            column1.setHeight(columnY.nextInt((screenHeight - columnGap) - topBorder) + topBorder);
            column2.setY(column1.getHeight() + columnGap);
            score++; // Increment the score by 1
        }

        // Check the collision between the player and the obstacles
        if(collision(player, column1)) {
            if(player.getAlive()) {
                goToGameOverActivity();
                player.setAlive(false);
            }
        } else if (collision(player, column2)) {
            if(player.getAlive()) {
                goToGameOverActivity();
                player.setAlive(false);
            }
        }
    }

    public boolean collision(Player player, Obstacle obstacle) {
        if(player.getX() + player.getWidth() >= obstacle.getX()) {
            if((player.getY() <= obstacle.getHeight()) && (player.getY() + player.getHeight() >= obstacle.getY())) {
                return true;
            }
        }
        return false;
    }

    public void goToGameOverActivity() {
        Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
        gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        gameOverIntent.putExtra("score", score); // Send the score data to the game over activity
        getContext().startActivity(gameOverIntent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Tap the screen to fly
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            screenTap = true;
            player.setSpeed(-20);
        }
        return true;
    }
}
