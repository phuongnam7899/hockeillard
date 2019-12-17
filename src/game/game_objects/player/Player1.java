package game.game_objects.player;
import game.GameWindow;
import game.game_objects.bullet.BulletPlayer1;
import game.game_objects.GameObject;
import libs.SpriteUtils;
import game.renderer.Animation;

public class Player1 extends Player {
    public static int plateNumber = 3;
    public static int stamina = 16;
    public Player1(){
        super();
        this.position.set(200,300);
        this.images.add(SpriteUtils.loadImage("assets/images/player/player1/SCREEN 2-18.png"));
        this.renderer = new Animation(this.images);
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

    private void fire() {
        if(GameWindow.isSpacePress){
            if(this.fireCounter.run()){
                BulletPlayer1 bullet = GameObject.recycle(BulletPlayer1.class);
                bullet.position.set(this.position);
                bullet.velocity.set(this.velocity);
                System.out.println(GameObject.gameObjects.size());
                this.fireCounter.reset();
            }
        }
    }

    private void sprint() {
        if(GameWindow.isShiftPress && Player1.stamina > 0){
            if(this.sprintCounter.run()){
                Player1.stamina--;
                this.velocity.setLength(this.velocity.getLength() + 3);
                this.maxVelocity = 10;
                this.sprintCounter.reset();
            }
        }else {
            this.maxVelocity = 5;
        }
    }

    @Override
    public void move(){
//        System.out.println(this.position.x + "-" + this.position.y);
//        System.out.println( (this.boxColider.center().x + Settings.PLAYER_RADIUS ) + "-" + (this.boxColider.center().y + Settings.PLAYER_RADIUS ) );
        if (this.velocityCounter.run()){
            if(GameWindow.isAPress){
                this.velocity.add(-1,0);
            }
            if(GameWindow.isDPress){

                this.velocity.add(1,0);
            }
            if(GameWindow.isSPress){
                this.velocity.add(0,1);
            }
            if(GameWindow.isWPress){
                this.velocity.add(0,-1);
            }
            this.velocityCounter.reset();
        }
        }
    public void reset(){
        this.position.set(200,300);
        this.velocity.set(0,0);
    }
}
