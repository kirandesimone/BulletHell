package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();
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

}
