package com.game.main;

import java.awt.*;
import java.util.Random;

public class EnemyProjectile extends GameObject {

    Handler handler;
    Random random = new Random();

    public EnemyProjectile(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        speedX = (random.nextInt(5 - -5) + -5);
        speedY = 5;
    }

    @Override
    public void tick() {
        x += speedX;
        y += speedY;
        if(y >= Game.HEIGHT) {
            handler.removeGameObject(this);
        }

        handler.addGameObject(new Trail((int) x,(int) y, ID.Trail, 16, 16, 0.1f, Color.blue, handler));

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect((int) x,(int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 16, 16);
    }
}

