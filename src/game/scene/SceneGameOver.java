package game.scene;

import game.game_objects.GameObject;
import game.game_objects.background.BackgroundGameOver;
import libs.AudioUtils;

import javax.sound.sampled.Clip;

public class SceneGameOver extends Scene {
    public static Clip sound;
    @Override
    public void init() {
        GameObject.recycle(BackgroundGameOver.class);
        this.sound = AudioUtils.loadSound("assets/sound/screen3/screen3_BGM.wav");
        this.sound.start();
    }
    @Override
    public void clear() {
        GameObject.clearAll();
        GameObject.deActiveAll();
        this.sound.stop();
    }

}
