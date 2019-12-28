package game.game_objects.player;

import game.GameWindow;
import game.game_objects.bullet.BulletPlayer2;
import game.game_objects.GameObject;
import libs.AudioUtils;
import libs.SpriteUtils;
import game.renderer.Animation;

import javax.sound.sampled.Clip;

public class Player2 extends Player {
    public static int plateNumber = 3;
    public static int stamina = 16;
    public Player2(){
        super();
        this.position.set(1100,300);
        this.images.add(SpriteUtils.loadImage("assets/images/player/player2/SCREEN 2-17.png"));
        this.renderer = new Animation(this.images);
    }
    @Override
    public void move(){
        if (this.velocityCounter.run()){
            if(GameWindow.isLeftPress){
                this.velocity.add(-1,0);
            }
            if(GameWindow.isRightPress){

                this.velocity.add(1,0);
            }
            if(GameWindow.isDownPress){
                this.velocity.add(0,1);
            }
            if(GameWindow.isUpPress){
                this.velocity.add(0,-1);
            }
            this.velocityCounter.reset();
        }

    }

    @Override
    public void run(){
        super.run();
        this.sprint();
        this.fire();
        this.staminaRecover();
    }
    private void staminaRecover() {
        if(this.staminaRecoverCounter.run() && stamina < 16){
            stamina++;
            this.staminaRecoverCounter.reset();
        }
    }
    private void sprint() {
        if(GameWindow.isbkSpacePress){
            if(this.sprintCounter.run() && Player2.stamina > 0){
                Player2.stamina--;
                this.velocity.setLength(this.velocity.getLength() + 3);
                this.maxVelocity = 10;
                this.sprintCounter.reset();
            }
        }else {
            this.maxVelocity = 5;
        }
    }
    private void fire() {
        if(GameWindow.isEnterPress){
            if(this.fireCounter.run()){
                BulletPlayer2 bullet = GameObject.recycle(BulletPlayer2.class);
                bullet.position.set(this.position);
                bullet.velocity.set(this.velocity);
                System.out.println(GameObject.gameObjects.size());
                this.fireCounter.reset();
                Clip sfx = AudioUtils.loadSound("assets/sound/Screen2/P2shoot2.wav");
                sfx.start();
            }
        }
    }
    public void reset(){
        this.position.set(1100,300);
        this.velocity.set(0,0);
    }
}
