package game.game_objects.amount_bar.amount_piece;

import game.game_objects.GameObject;
import game.game_objects.bullet.Bullet;
import game.renderer.SingleImageRenderer;
import libs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StaminaPiecePlayer1 extends GameObject {
    BufferedImage image;
    public StaminaPiecePlayer1(){
        GameObject.midLayer.add(this);
            this.image = SpriteUtils.loadImage("assets/images/background/background_playing/stamina/player1/SCREEN 2-10.png");
        this.renderer = new SingleImageRenderer(image);
    }
}
