package game.game_objects.bullet;

import game.Vector2D;
import game.game_objects.GameObject;
import game.game_objects.player.Player2;
import game.renderer.Animation;
import libs.SpriteUtils;

public class BulletPlayer1 extends Bullet {
    public BulletPlayer1() {
        super();
        this.images.add(SpriteUtils.loadImage("assets/images/bullet/Player1Bullet/SCREEN 2-23.png"));
        this.renderer = new Animation(this.images);
    }
    private void playerBounce() {
        Player2 player = GameObject.findIntersected(Player2.class,this.boxColider);
        if(player != null){
            Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
            Vector2D playerPosition = new Vector2D(player.position.x,player.position.y);
            Vector2D addVector = thisPosition.subtract(playerPosition);
            player.velocity.subtract(addVector.setLength(10));
//            this.deactive();
        }
    }
    @Override
    public void run() {
        super.run();
        this.playerBounce();
    }
}
