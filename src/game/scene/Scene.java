package game.scene;

import game.game_objects.GameObject;

import javax.sound.sampled.Clip;

public abstract class Scene {
    public Clip sound;
    public abstract void init();
    public void clear() {
        GameObject.clearAll();
        GameObject.deActiveAll();
    }
}
