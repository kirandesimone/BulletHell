package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {

    private Handler handler;
    private HUD hud;
    private Game game;

    public Shop(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void mousePressed(MouseEvent mouseEvent) {
        int mx = mouseEvent.getX();
        int my = mouseEvent.getY();

        if(isMouseOver(mx, my, 370, 375, 230, 35)) {
            game.gameState = STATE.BossMode;
            hud.setLevel(hud.getLevel() + 1);
            handler.addGameObject(new Boss((Game.WIDTH /  2) - 48, - 120 , ID.Boss, handler));
        }


    }

    private boolean isMouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    public void render(Graphics graphics) {
        Font font = new Font("arial", 1, 50);
        Font font1 = new Font("arial", 1, 20);

        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("Shop", 250, 70);

        graphics.setFont(font1);
        graphics.drawRect(370, 375, 230, 35);
        graphics.drawString("Continue to Boss Battle", 370, 400 );

        // ITEMS
        graphics.drawRect(110, 110, 120, 100);
        graphics.drawString("Item 1", 100, 100);
    }
}
