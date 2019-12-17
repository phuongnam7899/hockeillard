package game.game_objects.bot.tornado;

import game.FrameCounter;
import game.Settings;
import game.Vector2D;
import game.box_colider.BoxColider;
import game.game_objects.GameObject;
import game.game_objects.plate.Plate;
import game.game_objects.player.Player;
import game.renderer.Animation;
import interfaces.Physics;
import libs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tornado extends GameObject implements Physics {
    public BoxColider boxColider;
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private FrameCounter changeVelocityCounter = new FrameCounter(60);
    public Tornado(){
        GameObject.midLayer.add(this);
        this.images.add(SpriteUtils.loadImage("assets/images/bot/tornado/tornado1.png"));
        this.renderer = new Animation(this.images);
        this.boxColider = new BoxColider(this,Settings.TORNADO_RADIUS);
    }

    @Override
    public void run(){
        super.run();
        this.wander();
        this.limitVelocity();
        this.findObject();
    }

    private void wander() {

        if(this.changeVelocityCounter.run()){
//            System.out.println("wandering");
            float randomX = (float) Math.random()*20 - 10;
            float randomY = (float) Math.random()*20 - 10;
            this.velocity.set(randomX,randomY);
            this.changeVelocityCounter.reset();
        }
    }
    @Override
    public void limitPosition(){
        if (this.position.y > Settings.PLAYGROUND_BOT - Settings.TORNADO_RADIUS * 2 - 5) {
            this.position.y = Settings.PLAYGROUND_BOT - Settings.TORNADO_RADIUS * 2 - 5;
        }
        if (this.position.y < Settings.PLAYGROUND_LEFT + Settings.TORNADO_RADIUS * 2 + 5) {
            this.position.y = Settings.PLAYGROUND_LEFT + Settings.TORNADO_RADIUS * 2 + 5;
        }
        if (this.position.x < Settings.PLAYGROUND_TOP + Settings.TORNADO_RADIUS * 2 + 5) {
            this.position.x = Settings.PLAYGROUND_TOP + Settings.TORNADO_RADIUS * 2 + 5;
        }
        if (this.position.x > Settings.PLAYGROUND_RIGHT - Settings.TORNADO_RADIUS * 2 - 5) {
            this.position.x = Settings.PLAYGROUND_RIGHT - Settings.TORNADO_RADIUS * 2 - 5;
        }
    }
    public void limitVelocity(){
        if(this.velocity.getLength() > 2) this.velocity.setLength(2);
    }

    public void teleport(GameObject target){
        ArrayList<Tornado> tornadoes = GameObject.findObject(Tornado.class);
        for (int i = 0; i < tornadoes.size() ; i++) {
            Tornado tornado = tornadoes.get(i);

            if( tornado != this){
//                System.out.println(i);
//                this.position.print();
//                tornado.position.print();
                Vector2D tornadoPosition = tornado.position.clone() ;
                target.position.set(tornadoPosition);
                target.isTeleportable = false;
            }
        }
    }

    public void findObject(){
        Player player = GameObject.findIntersected(Player.class,this.boxColider);
        if(player != null && player.isTeleportable){
            this.teleport(player);
        }
        Plate plate = GameObject.findIntersected(Plate.class,this.boxColider);
        if(plate != null && plate.isTeleportable){
            this.teleport(plate);
        }
    }

    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }
}
