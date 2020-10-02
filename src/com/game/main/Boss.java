package com.game.main;

import java.awt.*;
import java.util.Random;

public class Boss extends GameObject {

    Handler handler;
    Random random = new Random();
    private int timer = 80;
    private int timer2 = 50;

    public Boss(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        speedX = 0;
        speedY = 2;
    }

    @Override
    public void tick() {
        x += speedX;
        y += speedY;

        if(timer <= 0) {
            speedY = 0;
            timer2--;
        } else {
            timer--;
        }

        if(timer2 <= 0) {
            if(speedX == 0) {
                speedX = 2;
            }
            int spawn = random.nextInt(10);
            if(spawn == 0) {
                handler.addGameObject(new BasicProjectile((int)x + 48,(int) y + 48, ID.BasicProjectile, handler));
            }
        }

        if(x <= 0 || x >= Game.WIDTH - 64) {
            speedX *= -1;
        }


        handler.addGameObject(new Trail((int) x,(int) y, ID.Trail, 64, 64, 0.1f, Color.red, handler));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect((int) x,(int) y, 64, 64);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 64, 64);
    }
}
