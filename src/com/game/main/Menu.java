package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent mouseEvent) {
        int mx = mouseEvent.getX();
        int my = mouseEvent.getY();

        if(game.gameState == STATE.Menu) {

            // PLAY
            if (isMouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = STATE.Game;
                Player player = new Player(100, 100, ID.Player, handler, game);
                handler.addGameObject(player);
                game.addMouseListener(player);
                handler.loadEnemies(handler);

            }

            // OPTIONS
            if (isMouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = STATE.Options;
            }

            // EXIT
            if (isMouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        if(game.gameState == STATE.Options) {
            if(isMouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                return;
            }
        }

        if(game.gameState == STATE.GameOver) {
            if(isMouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                hud.setScore(0);
                hud.setLevel(1);
            }
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {

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

    public void tick() {

    }

    public void render(Graphics graphics) {
        Font font = new Font("arial", 1, 50);
        Font font1 = new Font("arial", 1, 30);

        if(game.gameState == STATE.Options) {
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Options", 240, 70);

            graphics.setFont(font1);
            graphics.drawString("WASD to move", 270, 190);

            // BACK
            graphics.setFont(font1);
            graphics.drawRect(210, 350, 200, 64);
            graphics.drawString("Back", 270, 390);

        } else if (game.gameState == STATE.GameOver) {
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Game Over", 240, 70);

            graphics.setFont(font1);
            graphics.drawString("Score: " + hud.getScore() , 270, 290);

            graphics.drawRect(210, 350, 200, 64);
            graphics.drawString("Main Menu", 270, 390);

        } else{
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Menu", 240, 70);

            graphics.setFont(font1);
            graphics.drawRect(210, 150, 200, 64);
            graphics.drawString("Play", 270, 190);


            graphics.drawRect(210, 250, 200, 64);
            graphics.drawString("Options", 270, 290);

            graphics.drawRect(210, 350, 200, 64);
            graphics.drawString("Exit", 270, 390);
        }
    }



}
