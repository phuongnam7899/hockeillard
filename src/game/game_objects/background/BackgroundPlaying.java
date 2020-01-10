package game.game_objects.background;

import game.Settings;
import game.game_objects.GameObject;
import game.game_objects.player.Player1;
import game.game_objects.player.Player2;
import game.scene.SceneGameOver;
import game.scene.SceneManager;
import libs.SpriteUtils;
import game.renderer.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BackgroundPlaying extends GameObject {
    public BackgroundPlaying(){
        super();
        GameObject.botLayer.add(this);
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("assets/images/background/background_playing/static/static1.png"));
        this.renderer = new Animation(images);
        this.velocity.set(0, 0);
        this.position.set(Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2);
    }
    @Override
    public void run(){
        super.run();

        if(Player1.plateNumber == 0 || Player2.plateNumber == 0){
            SceneManager.signNewScene(new SceneGameOver());
            Player1.plateNumber = 3;
            Player2.plateNumber = 3;
            Player1.stamina = 16;
            Player2.stamina = 16;
        }
    }
}
