package game;

import javax.swing.*;
import game.game_objects.*;
import game.scene.SceneManager;
import game.scene.ScenePlaying;

import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel() {
        SceneManager.signNewScene(new ScenePlaying());
    }
    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 40;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastLoop > delay) {
                this.runAll();
                this.renderAll();
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        this.repaint();
    }

    private void runAll() {
        GameObject.runAll();
    }

    public void paint(Graphics g){
        GameObject.renderAll(g);
    }
}
