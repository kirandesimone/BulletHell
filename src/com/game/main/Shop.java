package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {

    private Handler handler;
    private HUD hud;

    public Shop(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent mouseEvent) {

    }

    public void render(Graphics graphics) {

    }
}
