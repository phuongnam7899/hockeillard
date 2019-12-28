package game.game_objects.background;

import game.GameWindow;
import game.Settings;
import game.game_objects.GameObject;
import game.renderer.Animation;
import game.scene.SceneManager;
import game.scene.ScenePlaying;
import libs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BackgroundMenu extends GameObject {
    public BackgroundMenu(){
        GameObject.botLayer.add(this);
        ArrayList<BufferedImage> images = new ArrayList<>();
        for (int i = 0; i < 10;i++){
            images.add(SpriteUtils.loadImage("assets/images/background/background_start/SCREEN1_0000" + i + ".png"));
        }
        for (int i = 10; i < 90;i++){
            images.add(SpriteUtils.loadImage("assets/images/background/background_start/SCREEN1_000" + i + ".png"));
        }
        this.renderer = new Animation(images);
        this.position.set(Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2);
    }

    @Override
    public void run() {
        super.run();
        if(GameWindow.isAnyKeyPress){
            SceneManager.signNewScene(new ScenePlaying());
        }
    }
}
