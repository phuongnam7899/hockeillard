package game.game_objects.background;

import game.Settings;
import game.game_objects.GameObject;
import game.game_objects.player.Player1;
import game.renderer.Animation;
import libs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BackgroundGameOver extends  GameObject {
    public BackgroundGameOver(){
        GameObject.botLayer.add(this);
        ArrayList<BufferedImage> images = new ArrayList<>();
//        images.add(SpriteUtils.loadImage("assets/images/background/bg_1.jpg"));
        this.renderer = new Animation(images);
        this.velocity.set(0, 0);
        this.position.set(Settings.SCREEN_WIDTH / 2
                , Settings.SCREEN_HEIGHT / 2);
    }
    @Override
    public void render(Graphics g){
        super.render(g);
        if(Player1.plateNumber == 0){
            g.drawString("p2 win",300,300);
        }else {
            g.drawString("p1 win",300,300);
        }
        System.out.println(GameObject.gameObjects.size());
    }
}
