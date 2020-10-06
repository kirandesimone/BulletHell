package com.game.main;

import java.util.List;
import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random random;
    private Game game;

    private int scoreKeep = 0;
    private List<GameObject> enemies;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.random = new Random();
        this.enemies = handler.getEnemies();
        this.game = game;
    }

    public void tick() {
        scoreKeep++;

        if(scoreKeep >=  50) {
            scoreKeep = 0;

            if(hud.getLevel() % 10 >= 1 && hud.getLevel() % 10 < 9) {
                GameObject enemy = enemies.get(random.nextInt(enemies.size()));
                if(enemy.getId() == ID.BasicEnemy) {
                    handler.addGameObject(new BasicEnemy(80, 120, ID.BasicEnemy, handler));
                }else if(enemy.getId() == ID.SmartEnemy) {
                    handler.addGameObject(new SmartEnemy(90, 110, ID.SmartEnemy, handler));
                }
                hud.setLevel(hud.getLevel() + 1);
            }
        }
    }

}
