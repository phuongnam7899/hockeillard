package game.game_objects.amount_bar.amount_piece;

import game.game_objects.GameObject;
import game.renderer.SingleImageRenderer;
import libs.SpriteUtils;

import java.awt.image.BufferedImage;

public class PlateNumberPiecePlayer1 extends GameObject {
        public PlateNumberPiecePlayer1(){
            GameObject.topLayer.add(this);
            BufferedImage image = SpriteUtils.loadImage("assets/images/background/background_playing/plate_number/player1/SCREEN 2-07.png");
            this.renderer = new SingleImageRenderer(image);
        }
    }

