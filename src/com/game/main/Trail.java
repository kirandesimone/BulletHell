package com.game.main;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha = 1;
    private int width;
    private int height;
    private float life;
    Handler handler;
    Color color;

    public Trail(int x, int y, ID id, int width, int height, float life, Color color, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;

    }

    @Override
    public void tick() {
        if(alpha > life) {
            alpha -= (life - 0.001f);
        }else {
            handler.removeGameObject(this);
        }

    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setComposite(makeTransparent(alpha));

        graphics.setColor(color);
        graphics.fillRect((int) x,(int) y, width, height);

        graphics2D.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
