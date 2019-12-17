package game.game_objects.bot.hole_keeper;

import game.Settings;
import game.Vector2D;
import game.box_colider.BoxColider;
import game.game_objects.GameObject;
import game.game_objects.player.Player;
import game.renderer.Animation;
import interfaces.Physics;
import libs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HoleKeeper extends GameObject implements Physics {
    ArrayList<BufferedImage> images = new ArrayList<>();
    BoxColider boxColider;
    public HoleKeeper(){
        GameObject.midLayer.add(this);
        this.images.add(SpriteUtils.loadImage("assets/images/bot/goal_keeper/SCREEN 2-15.png"));
        this.renderer = new Animation(this.images);
        this.boxColider = new BoxColider(this, Settings.H_KEEPER_RADIUS);
    }

    @Override
    public void  run(){
        super.run();
        this.limitPosition();
        this.playerBounce();
    }

    private void playerBounce() {
        Player player = GameObject.findIntersected(Player.class, this.boxColider);
        if(player != null){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D otherPosition = new Vector2D(player.position.x,player.position.y);
            Vector2D addVector = thisPosition.subtract(otherPosition);
            player.velocity.subtract(addVector.setLength(this.velocity.getLength() * (float)1.5));
            player.position.subtract(addVector.setLength(5));
        }
    }

    @Override
    public void limitPosition(){
        if (this.position.y > Settings.PLAYGROUND_BOT - Settings.PLAYER_RADIUS) {
            this.position.y = Settings.PLAYGROUND_BOT - Settings.PLAYER_RADIUS;
            this.velocity.reverse();
        }
        if (this.position.y < Settings.PLAYGROUND_TOP + Settings.PLAYER_RADIUS) {
            this.position.y = Settings.PLAYGROUND_TOP + Settings.PLAYER_RADIUS;
            this.velocity.reverse();
        }
        if (this.position.x < Settings.PLAYGROUND_LEFT + Settings.PLAYER_RADIUS) {
            this.position.x = Settings.PLAYGROUND_LEFT + Settings.PLAYER_RADIUS;
            this.velocity.reverse();
        }
        if (this.position.x > Settings.PLAYGROUND_RIGHT - Settings.PLAYER_RADIUS) {
            this.position.x = Settings.PLAYGROUND_RIGHT - Settings.PLAYER_RADIUS ;
            this.velocity.reverse();
        }
    }

    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }
}
