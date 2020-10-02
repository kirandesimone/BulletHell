package com.game.main;

import java.awt.*;

public class SmartEnemy extends GameObject{

    Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for(int i = 0; i < handler.objects.size(); i++) {
            if(handler.objects.get(i).getId() == ID.Player) {
                player = handler.objects.get(i);
            }
        }

    }

    @Override
    public void tick() {
        x += speedX;
        y += speedY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));

        speedX = ((-1/distance) * diffX);
        speedY = ((-1/distance) * diffY);



        // if it hits the top of the box then flip velocity to go the other way
        if(y <= 0 || y >= Game.HEIGHT - 32) {
                speedY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH - 16) {
            speedX *= -1;
        }

        handler.addGameObject(new Trail((int) x,(int) y, ID.Trail, 16, 16, 0.1f, Color.green, handler));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillRect((int) x,(int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
            return new Rectangle((int) x,(int) y, 16, 16);
        }

}


