package game.scene;

import game.game_objects.GameObject;
import game.game_objects.background.BackgroundGameOver;

public class SceneGameOver extends Scene {
    @Override
    public void init() {
        GameObject.recycle(BackgroundGameOver.class);
    }
}
