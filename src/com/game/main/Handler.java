package com.game.main;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();
    List<GameObject> enemies = new ArrayList<>();
    private Game game;

    public Handler(Game game) {
        this.game = game;
    }

    public void tick() {
        for(int i = 0; i < objects.size(); i++) {
            GameObject tempGameObject = objects.get(i);

            tempGameObject.tick();
        }
    }

    public void render(Graphics graphics) {
        for(int i = 0; i < objects.size(); i++) {
            GameObject tempGameObject = objects.get(i);

            tempGameObject.render(graphics);
        }
    }

    public void clearRound() {
        for(int i = 0; i < objects.size(); i++) {
            GameObject gameObject = objects.get(i);
            if(gameObject.getId() != ID.Player) {
                removeGameObject(gameObject);
                i--;
            }
            if(game.gameState == STATE.GameOver) {
                objects.remove();
            }
        }
    }

    public void addGameObject(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    public List<GameObject> getEnemies() {
        return this.enemies;
    }

    public void loadEnemies(Handler handler) {
        enemies.add(new BasicEnemy(80, 120, ID.BasicEnemy, handler));
        enemies.add(new SmartEnemy(90, 110, ID.SmartEnemy, handler));
    }


}