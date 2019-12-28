package game.game_objects.bullet;

import game.FrameCounter;
import game.Settings;
import game.Vector2D;
import game.box_colider.BoxColider;
import game.game_objects.GameObject;
import game.game_objects.plate.Plate;
import game.renderer.Animation;
import interfaces.Physics;
import libs.AudioUtils;
import libs.SpriteUtils;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bullet extends GameObject implements Physics {
    ArrayList<BufferedImage> images = new ArrayList<>();
    BoxColider boxColider;
    FrameCounter autoDeactiveCounter = new FrameCounter(180);
    public Bullet(){
        GameObject.midLayer.add(this);

        this.boxColider = new BoxColider(this, Settings.BULLET_RADIUS);
    }

    @Override
    public void run() {
        super.run();
        this.limitVelocity();
        this.autoDeactive();
        this.wallBounce();
        this.plateBounce();
    }
    public void wallBounce(){
        if ((this.position.y > Settings.PLAYGROUND_BOT - Settings.PLAYER_RADIUS) || this.position.y < Settings.PLAYGROUND_TOP + Settings.PLAYER_RADIUS) {
            this.velocity.symmetryX();
        }
        if ((this.position.x < Settings.PLAYGROUND_LEFT + Settings.PLAYER_RADIUS) || (this.position.x > Settings.PLAYGROUND_RIGHT - Settings.PLAYER_RADIUS) ) {
            this.velocity.symmetryY();
        }
    }
    private void autoDeactive() {
        if(this.autoDeactiveCounter.run()){
            this.deactive();
            this.autoDeactiveCounter.reset();
        }
    }

    private void limitVelocity() {
        if(this.velocity.getLength() != 15){
            this.velocity.setLength(15);
        }
    }
    private void plateBounce() {
        Plate plate = GameObject.findIntersected(Plate.class,this.boxColider);
        if(plate != null){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D otherPosition = new Vector2D(plate.position.x,plate.position.y);
            Vector2D addVector = thisPosition.subtract(otherPosition);
            plate.velocity.subtract(addVector.setLength(10));
            this.deactive();
            Clip sfx = AudioUtils.loadSound("assets/sound/Screen2/disk_to_everything.wav");
            sfx.start();
        }
    }
    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }
}
