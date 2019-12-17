package game.game_objects.player;

import game.FrameCounter;
import game.GameWindow;
import game.Settings;
import game.Vector2D;
import game.game_objects.GameObject;
import game.box_colider.BoxColider;
//import interfaces.Moveable;
import interfaces.Physics;
import game.renderer.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

    public class Player extends GameObject implements Physics {
        BoxColider boxColider;
        ArrayList<BufferedImage> images = new ArrayList<>();
        public FrameCounter velocityCounter = new FrameCounter(4);
        public FrameCounter sprintCounter = new FrameCounter(30);
        public FrameCounter fireCounter = new FrameCounter(1);
        public FrameCounter staminaRecoverCounter = new FrameCounter(120);

        public int maxVelocity;
        public Player() {
            GameObject.midLayer.add(this);
            this.boxColider = new BoxColider(this, Settings.PLAYER_RADIUS);
            this.renderer = new Animation(this.images);
            this.maxVelocity = 5;
        }

        @Override
        public void run() {
//            System.out.println(GameObject.gameObjects.size());
            super.run();
            this.move();
            this.limitPosition();
            this.limitVelocity();
            this.playerBounce();
            this.getFriction((float) 0.03);
        }


        private void playerBounce() {
            Player otherPlayer = GameObject.findIntersected(Player.class,this.boxColider);
            if(otherPlayer != null && otherPlayer != this){
                Vector2D thisPosition = new Vector2D(this.position.x,this.position.y);
                Vector2D otherPosition = new Vector2D(otherPlayer.position.x,otherPlayer.position.y);
                Vector2D addVector = thisPosition.subtract(otherPosition);
                this.velocity.add(addVector.setLength(otherPlayer.velocity.getLength()));
                otherPlayer.position.subtract(addVector.setLength(5));
               this.position.add(addVector);
                otherPlayer.velocity.subtract(addVector);
            }
        }
        public void move() {
        }
        public static void resetAll() {
            ArrayList<Player1> player1 = GameObject.findObject(Player1.class);
            ArrayList<Player2> player2 = GameObject.findObject(Player2.class);
            for (int i = 0; i < player1.size() ; i++) {
                player1.get(i).reset();
            }
            for (int i = 0; i < player2.size() ; i++) {
                player2.get(i).reset();
            }
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

        public void limitVelocity(){
            if (this.velocity.getLength() > this.maxVelocity){
                this.velocity.setLength(this.maxVelocity);
            }
        }

        @Override
        public BoxColider getBoxColider() {
            return this.boxColider;
        }
    }
