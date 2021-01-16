package com.example.flappyface;

import android.graphics.Color;
import android.graphics.Paint;

public class Obstacle extends GenericObject {
    private Paint obstaclePaint;

    public Obstacle(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
        obstaclePaint = new Paint();
        obstaclePaint.setColor(Color.BLACK);
    }

    public void move() {
        x -= speed;
    }

    public Paint getObstaclePaint() {
        return obstaclePaint;
    }

    public void setObstaclePaint(Paint obstaclePaint) {
        this.obstaclePaint = obstaclePaint;
    }
}
