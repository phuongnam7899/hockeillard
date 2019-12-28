package game.scene;

import game.game_objects.GameObject;
import game.game_objects.background.BackgroundMenu;
import libs.AudioUtils;

import javax.sound.sampled.Clip;

public class SceneMenu extends Scene {
    public static Clip sound;
    @Override
    public void init() {
        GameObject.recycle(BackgroundMenu.class);
        Clip introSound = AudioUtils.loadSound("assets/sound/screen1/Opening-sound.wav");
        introSound.start();
        this.sound = AudioUtils.loadSound("assets/sound/screen1/Screen1_BGM.wav");
        this.sound.start();
    }

    @Override
    public void clear() {
         super.clear();
        this.sound.stop();
    }
}
