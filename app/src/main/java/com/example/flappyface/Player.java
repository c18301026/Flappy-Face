package com.example.flappyface;

import android.graphics.Bitmap;

public class Player extends GenericObject {
    private Bitmap sprite;
    private Boolean alive;

    public Player(int x, int y, int width, int height, int speed, Bitmap sprite) {
        super(x, y, width, height, speed);
        this.sprite = sprite;
        alive = true;
    }

    // Player's character is pushed down by gravity
    public void gravity() {
        y += speed;
        speed += 1;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}
