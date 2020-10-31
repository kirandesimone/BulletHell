package com.game.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player extends GameObject implements MouseListener {

    private Handler handler;
    private Game game;

    public Player(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }



    @Override
    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        System.out.println(mX);
        System.out.println(mY);

        if(game.gameState == STATE.Game || game.gameState == STATE.BossMode) {
            handler.addGameObject(new PlayerProjectile(x + 15, y, ID.PlayerProjectile, handler, mX, -mY));
            System.out.println("Shooting");
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

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
            GameObject tempGameObject = handler.objects.get(i);// Enemy Types

            if(tempGameObject.getId() == ID.BasicEnemy || tempGameObject.getId() == ID.SmartEnemy
                    || tempGameObject.getId() == ID.EnemyProjectile) {
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


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
