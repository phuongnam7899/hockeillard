package game.game_objects.hole;

import game.Settings;
import game.box_colider.BoxColider;
import game.game_objects.GameObject;
import game.game_objects.plate.Plate;
import game.game_objects.player.Player1;
import game.game_objects.player.Player2;
import game.renderer.Animation;
import interfaces.Physics;
import libs.AudioUtils;
import libs.SpriteUtils;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hole extends GameObject implements Physics {
    BoxColider boxColider;
    ArrayList<BufferedImage> images = new ArrayList<>();
    public Hole(){
        GameObject.midLayer.add(this);
        this.images.add(SpriteUtils.loadImage("assets/images/hole/SCREEN 2-22.png"));
        this.renderer = new Animation(this.images);
        this.boxColider = new BoxColider(this, Settings.HOLE_RADIUS - 5);
//        System.out.println(this.images.get(0).getWidth());
    }
    @Override
    public void  run(){
        super.run();
        this.hitted();
    }
    public void hitted(){
        Plate plate = GameObject.findIntersected(Plate.class,this.boxColider);
        if (plate != null){
            if(plate.teamNumber == 1) Player1.plateNumber--;
            else Player2.plateNumber--;
            plate.deactive();
            if(Player1.plateNumber == 1 || Player2.plateNumber == 1){
                Clip sfx = AudioUtils.loadSound("assets/sound/Screen2/last_disk_before_end_screen.wav");
                sfx.start();
            }else {
                Clip sfx = AudioUtils.loadSound("assets/sound/Screen2/disk_to_hole.wav");
                sfx.start();
            }

        }
    }
    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }
}
