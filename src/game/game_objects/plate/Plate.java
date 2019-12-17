package game.game_objects.plate;

import game.Settings;
import game.Vector2D;
import game.box_colider.BoxColider;
import game.game_objects.GameObject;
//import interfaces.Moveable;
import game.game_objects.bot.hole_keeper.HoleKeeper;
import game.game_objects.player.Player;
import interfaces.Physics;
import libs.SpriteUtils;
import game.renderer.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Plate extends GameObject implements Physics {
    BoxColider boxColider;
    ArrayList<BufferedImage> images = new ArrayList<>();
    public int teamNumber;
    public Plate(int teamNumber){
        GameObject.midLayer.add(this);
        this.teamNumber = teamNumber;
        this.boxColider = new BoxColider(this, Settings.PLATE_RADIUS);
        if(this.teamNumber == 1){
            this.images.add(SpriteUtils.loadImage("assets/images/ball/BallPlayer1/SCREEN 2-13.png"));
        }else {
            this.images.add(SpriteUtils.loadImage("assets/images/ball/BallPlayer2/SCREEN 2-12.png"));
        }
        this.renderer = new Animation(this.images);

    }
    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }
    @Override
    public void run() {
        super.run();
        this.bounce();
        this.limitVelocity();
        this.getFriction((float)0.02);
    }
    @Override
    public void limitPosition(){
        if (this.position.y > Settings.PLAYGROUND_BOT - Settings.PLAYER_RADIUS) {
            this.position.y = Settings.PLAYGROUND_BOT - Settings.PLAYER_RADIUS;
        }
        if (this.position.y < Settings.PLAYGROUND_TOP + Settings.PLAYER_RADIUS) {
            this.position.y = Settings.PLAYGROUND_TOP + Settings.PLAYER_RADIUS;
        }
        if (this.position.x < Settings.PLAYGROUND_LEFT + Settings.PLAYER_RADIUS) {
            this.position.x = Settings.PLAYGROUND_LEFT + Settings.PLAYER_RADIUS;
        }
        if (this.position.x > Settings.PLAYGROUND_RIGHT - Settings.PLAYER_RADIUS) {
            this.position.x = Settings.PLAYGROUND_RIGHT - Settings.PLAYER_RADIUS ;
        }
    }
    private void bounce() {
        this.wallBounce();
        this.playerBounce();
        this.plateBounce();
        this.holeKeeperBounce();
    }

    private void holeKeeperBounce() {
        HoleKeeper holeKeeper = GameObject.findIntersected(HoleKeeper.class,this.boxColider);
        if(holeKeeper != null){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D otherPosition = new Vector2D(holeKeeper.position.x,holeKeeper.position.y);
            Vector2D addVector = thisPosition.subtract(otherPosition);
            this.velocity.add(addVector.setLength(holeKeeper.velocity.getLength() * (float)1.5));
        }
    }

    private void plateBounce() {
        Plate otherPlate = GameObject.findIntersected(Plate.class,this.boxColider);
        if(otherPlate != null && otherPlate != this){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D otherPosition = new Vector2D(otherPlate.position.x,otherPlate.position.y);
            Vector2D addVector = thisPosition.subtract(otherPosition);
            this.velocity.add(addVector.setLength(otherPlate.velocity.getLength()));
            otherPlate.position.subtract(addVector.setLength(5));
            otherPlate.velocity.subtract(addVector);
        }
    }

    private void playerBounce() {
        Player player = GameObject.findIntersected(Player.class,this.boxColider);
        if(player != null){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D playerPosition = new Vector2D(player.position.x,player.position.y);
            Vector2D addVector = thisPosition.subtract(playerPosition);
            this.velocity.add(addVector.setLength(player.velocity.getLength() * (float) 1.5));
            player.position.subtract(addVector.setLength(10));
        }
    }
    public void reset(){
        this.position.set(Settings.SCREEN_WIDTH/2,200);
        this.velocity.set(0,0);
    }
    public void limitVelocity(){
        if (this.velocity.getLength() > 10){
            this.velocity.setLength(10);
        }
    }
    public void wallBounce(){
        if ((this.position.y >= Settings.PLAYGROUND_BOT - Settings.PLAYER_RADIUS) || this.position.y <= Settings.PLAYGROUND_TOP + Settings.PLAYER_RADIUS) {
            this.velocity.symmetryX();
        }
        if ((this.position.x <= Settings.PLAYGROUND_LEFT + Settings.PLAYER_RADIUS) || (this.position.x >= Settings.PLAYGROUND_RIGHT - Settings.PLAYER_RADIUS) ) {
                this.velocity.symmetryY();
            }
    }

}
