package com.game.main;

import java.awt.*;

public class PlayerProjectile extends GameObject {

    Handler handler;

    public PlayerProjectile(float x, float y, ID id, Handler handler, int mouseX, int mouseY) {
        super(x, y, id);
        this.handler = handler;
        speedX = mouseX / x;
        speedY = mouseY / 15;
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
