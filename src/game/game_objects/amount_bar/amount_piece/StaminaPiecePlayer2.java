package game.game_objects.amount_bar.amount_piece;

import game.game_objects.GameObject;
import game.game_objects.bullet.Bullet;
import game.renderer.SingleImageRenderer;
import libs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StaminaPiecePlayer2 extends GameObject {
    BufferedImage image;
    public StaminaPiecePlayer2(){
        GameObject.midLayer.add(this);
        this.image = SpriteUtils.loadImage("assets/images/background/background_playing/stamina/player2/SCREEN 2-04.png");
        this.renderer = new SingleImageRenderer(image);
    }
}
