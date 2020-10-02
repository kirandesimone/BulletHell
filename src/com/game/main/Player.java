package com.game.main;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += speedX;
        y += speedY;

        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        collision();

    }

    private void collision() {
        for(int i = 0; i < handler.objects.size(); i++) {
            GameObject tempGameObject = handler.objects.get(i);// BasicEnemy

            if(tempGameObject.getId() == ID.BasicEnemy || tempGameObject.getId() == ID.SmartEnemy
                    || tempGameObject.getId() == ID.BasicProjectile) {
                if(getBounds().intersects(tempGameObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect((int) x,(int) y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 32, 32);
    }


}
