package com.game.main;

import java.awt.*;

public class HUD {

    public static int HEALTH = 100;
    private int greenValue = 255;

    private int score = 0;
    private int level = 9;

    public void tick() {
        HEALTH = (int) Game.clamp(HEALTH, 0, 100);
        greenValue = (int) Game.clamp(greenValue, 0, 255);
        score++;
        greenValue = HEALTH * 2;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.gray);
        graphics.fillRect(15, 15,200,32);
        graphics.setColor(new Color(75, greenValue, 0));
        graphics.fillRect(15, 15,HEALTH * 2,32);
        graphics.setColor(Color.white);
        graphics.drawRect(15, 15,200,32);

        graphics.drawString("Score " + score, 15, 64);
        graphics.drawString("Level " + level, 15, 80);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
