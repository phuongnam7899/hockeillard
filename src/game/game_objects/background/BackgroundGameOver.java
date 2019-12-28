package game.game_objects.background;

import game.GameWindow;
import game.Settings;
import game.game_objects.GameObject;
import game.game_objects.player.Player1;
import game.renderer.Animation;
import game.scene.SceneManager;
import game.scene.ScenePlaying;
import libs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BackgroundGameOver extends  GameObject {
    public BackgroundGameOver(){
        GameObject.botLayer.add(this);
        ArrayList<BufferedImage> images = new ArrayList<>();

        if(Player1.plateNumber == 0) {
            for (int i = 0; i < 10;i++){
                images.add(SpriteUtils.loadImage("assets/images/background/background_gameover/player_win/player_2/player2win_0000" + i + ".png"));
            }
            for (int i = 10; i < 90;i++){
                images.add(SpriteUtils.loadImage("assets/images/background/background_gameover/player_win/player_2/player2win_000" + i + ".png"));
        }
        }else {
            for (int i = 0; i < 10;i++){
                images.add(SpriteUtils.loadImage("assets/images/background/background_gameover/player_win/player_1/player1win_0000" + i + ".png"));
            }
            for (int i = 10; i < 90;i++){
                images.add(SpriteUtils.loadImage("assets/images/background/background_gameover/player_win/player_1/player1win_000" + i + ".png"));
            };
        }
        this.renderer = new Animation(images);
        this.position.set(Settings.SCREEN_WIDTH / 2
                , Settings.SCREEN_HEIGHT / 2);
    }

    @Override
    public void run() {
        if(GameWindow.isAnyKeyPress){
            SceneManager.signNewScene(new ScenePlaying());
        }
    }
}
