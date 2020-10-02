package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerController extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public PlayerController(Handler handler) {
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++) {
            GameObject tempGameObject = handler.objects.get(i);
            //tempGameObject == player1
            if(tempGameObject.getId() == ID.Player) {
                if(key == KeyEvent.VK_W) {
                    tempGameObject.setSpeedY(-5);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S) {
                    tempGameObject.setSpeedY(5);
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_A) {
                    tempGameObject.setSpeedX(-5);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_D) {
                    tempGameObject.setSpeedX(5);
                    keyDown[3] = true;
                }
            }
        }

    }

    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++) {
            GameObject tempGameObject = handler.objects.get(i);
            //tempGameObject == player1
            if(tempGameObject.getId() == ID.Player) {
                if(key == KeyEvent.VK_W) {
                    //tempGameObject.setSpeedY(0);
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_S) {
                    //tempGameObject.setSpeedY(0);
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_A) {
                    //tempGameObject.setSpeedX(0);
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_D) {
                    //tempGameObject.setSpeedX(0);
                    keyDown[3] = false;
                }

                // Vertical Movement
                if(!keyDown[0] && !keyDown[1]) {
                    tempGameObject.setSpeedY(0);
                }
                // Horizontal Movement
                if(!keyDown[2] && !keyDown[3]) {
                    tempGameObject.setSpeedX(0);
                }
            }
        }
    }
}
