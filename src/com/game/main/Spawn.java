package com.game.main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random random;

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        this.random = new Random();
    }

    public void tick() {
        scoreKeep++;

        if(scoreKeep >=  200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if(hud.getLevel() == 2) {
                handler.addGameObject(new Boss((Game.WIDTH / 2), -100, ID.Boss, handler));
            }
        }
    }
}
