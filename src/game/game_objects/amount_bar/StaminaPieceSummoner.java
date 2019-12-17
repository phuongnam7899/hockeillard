package game.game_objects.amount_bar;

import game.Settings;
import game.Vector2D;
import game.game_objects.GameObject;
import game.game_objects.amount_bar.amount_piece.StaminaPiecePlayer1;
import game.game_objects.amount_bar.amount_piece.StaminaPiecePlayer2;
import game.game_objects.player.Player1;
import game.game_objects.player.Player2;

import java.util.ArrayList;

public class StaminaPieceSummoner extends GameObject {
    public int pieceNumber1;
    public int pieceNumber2;

    public StaminaPieceSummoner(){
        GameObject.topLayer.add(this);
        this.pieceNumber1 = 0;
        this.pieceNumber2 = 0;
    }

    @Override
    public void run() {
        super.run();
        this.pieceSummon();
    }
    public void reset(){
        ArrayList<StaminaPiecePlayer1> piece1 = GameObject.findObject(StaminaPiecePlayer1.class);
        for (int i = 0; i < piece1.size() ; i++) {
//            piece1.get(i).position.print();
            piece1.get(i).deactive();
        }
        ArrayList<StaminaPiecePlayer2> piece2 = GameObject.findObject(StaminaPiecePlayer2.class);
        for (int i = 0; i < piece2.size() ; i++) {
            piece2.get(i).deactive();
        }
        this.pieceNumber1 = 0;
        this.pieceNumber2 = 0;
    }
    private void pieceSummon() {
        Vector2D staminaBarPlayer1 = new Vector2D(Settings.PLAYGROUND_LEFT - 63 - 20,Settings.PLAYGROUND_BOT - 10);
        Vector2D staminaBarPlayer2 = new Vector2D(Settings.PLAYGROUND_RIGHT + 63 + 20,Settings.PLAYGROUND_BOT -10);
        this.reset();
        for (int i = 0; i < Player1.stamina; i++ ){
            if(this.pieceNumber1 < Player1.stamina){
                System.out.println(GameObject.gameObjects.size());
                StaminaPiecePlayer1 piece = GameObject.recycle(StaminaPiecePlayer1.class);
                piece.position.set(staminaBarPlayer1).subtract(0,24*i);
                this.pieceNumber1++;
            }
         }
        for (int i = 0; i < Player2.stamina; i++ ){
            if(this.pieceNumber2 < Player2.stamina){
                StaminaPiecePlayer2 piece = GameObject.recycle(StaminaPiecePlayer2.class);
                piece.position.set(staminaBarPlayer2).subtract(0,24*i);
                this.pieceNumber2++;
            }
        }
    }
}
