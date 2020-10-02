package com.game.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    Handler handler;

    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        speedX = 5;
        speedY = 5;
    }

    @Override
    public void tick() {
        x += speedX;
        y += speedY;
        // if it hits the top of the box then flip velocity to go the other way
        if(y <= 0 || y >= Game.HEIGHT - 32) {
            speedY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH - 16) {
            speedX *= -1;
        }

        handler.addGameObject(new Trail((int) x,(int) y, ID.Trail, 16, 16, 0.1f, Color.red, handler));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect((int) x,(int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 16, 16);
    }
}
