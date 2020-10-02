package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean isRunning = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;
    private Shop shop;

    public STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler(this);
        hud = new HUD();
        shop = new Shop(handler, hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new PlayerController(handler));
        this.addMouseListener(menu);

        spawn = new Spawn(handler, hud);

        new Window(WIDTH, HEIGHT, "BulletHell!", this);

        if(gameState == STATE.Game) {
           // handler.addGameObject(new Player(100,100, ID.Player, handler));
           // handler.addGameObject(new Boss((Game.WIDTH / 2), -100, ID.Boss, handler));
        }

    }

    public static void main(String args[]) {
        new Game();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(isRunning) {
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        if(gameState == STATE.Game) {
            hud.tick();
            spawn.tick();
        } else if(gameState == STATE.Menu || gameState == STATE.GameOver){
            menu.tick();
        }

        if(HUD.HEALTH <= 0) {
            gameState = STATE.GameOver;
            HUD.HEALTH = 100;
            handler.clearRound();
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);
        if(gameState == STATE.Game) {
            hud.render(graphics);
            handler.render(graphics);
        } else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.GameOver) {
            menu.render(graphics);
            handler.render(graphics);
        } else if(gameState == STATE.Shop) {
            shop.render(graphics);
        }



        graphics.dispose();
        bs.show();
    }


    // Keep player in the room/box
    public static float clamp(float num, float min, float max) {
        if(num >= max) {
            return num = max;
        } else if(num <= min){
            return num = min;
        } else {
            return num;
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop() {
       try {
           thread.join(); // stopping the thread
           isRunning = false;
       } catch(Exception e) {
           e.printStackTrace();
       }
    }

}
