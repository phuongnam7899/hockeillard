package game.game_objects.bullet;

import game.Vector2D;
import game.game_objects.GameObject;
import game.game_objects.player.Player1;
import game.renderer.Animation;
import libs.AudioUtils;
import libs.SpriteUtils;

import javax.sound.sampled.Clip;

public class BulletPlayer2 extends Bullet {
    public BulletPlayer2() {
        super();
        this.images.add(SpriteUtils.loadImage("assets/images/bullet/Player2Bullet/SCREEN 2-16.png"));
        this.renderer = new Animation(this.images);
    }
    private void playerBounce() {
        Player1 player = GameObject.findIntersected(Player1.class,this.boxColider);
        if(player != null){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D playerPosition = new Vector2D(player.position.x,player.position.y);
            Vector2D addVector = thisPosition.subtract(playerPosition);
            player.velocity.subtract(addVector.setLength(10));
            this.deactive();
            Clip sfx = AudioUtils.loadSound("assets/sound/Screen2/Bullet_to_player.wav");
            sfx.start();
        }
    }
    @Override
    public void run() {
        super.run();
        this.playerBounce();
    }
}
