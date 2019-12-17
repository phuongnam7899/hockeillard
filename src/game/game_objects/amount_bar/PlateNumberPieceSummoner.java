package game.game_objects.amount_bar;

import game.Settings;
import game.Vector2D;
import game.game_objects.GameObject;
import game.game_objects.amount_bar.amount_piece.PlateNumberPiecePlayer1;
import game.game_objects.amount_bar.amount_piece.PlateNumberPiecePlayer2;
import game.game_objects.amount_bar.amount_piece.StaminaPiecePlayer1;
import game.game_objects.amount_bar.amount_piece.StaminaPiecePlayer2;
import game.game_objects.player.Player1;
import game.game_objects.player.Player2;

import java.util.ArrayList;
import java.util.Set;

public class PlateNumberPieceSummoner extends GameObject {
    public int pieceNumber1;
    public int pieceNumber2;

    public PlateNumberPieceSummoner(){
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
        ArrayList<PlateNumberPiecePlayer1> piece1 = GameObject.findObject(PlateNumberPiecePlayer1.class);
        for (int i = 0; i < piece1.size() ; i++) {
//            piece1.get(i).position.print();
            piece1.get(i).deactive();
        }
        ArrayList<PlateNumberPiecePlayer2> piece2 = GameObject.findObject(PlateNumberPiecePlayer2.class);
        for (int i = 0; i < piece2.size() ; i++) {
            piece2.get(i).deactive();
        }
        this.pieceNumber1 = 0;
        this.pieceNumber2 = 0;
    }
    private void pieceSummon() {
        Vector2D staminaBarPlayer1 = new Vector2D(Settings.PLAYGROUND_LEFT + 150, Settings.PLAYGROUND_BOT + 67 + 20);
        Vector2D staminaBarPlayer2 = new Vector2D(Settings.PLAYGROUND_RIGHT - 150,Settings.PLAYGROUND_BOT + 67 + 20);
        this.reset();
        for (int i = 0; i < Player1.plateNumber; i++ ){
            if(this.pieceNumber1 < Player1.plateNumber){
                PlateNumberPiecePlayer1 piece = GameObject.recycle(PlateNumberPiecePlayer1.class);
                piece.position.set(staminaBarPlayer1).add(67*i,0);
                this.pieceNumber1++;
            }
        }
        for (int i = 0; i < Player2.plateNumber; i++ ){
            if(this.pieceNumber2 < Player2.plateNumber){
                PlateNumberPiecePlayer2 piece = GameObject.recycle(PlateNumberPiecePlayer2.class);
                piece.position.set(staminaBarPlayer2).subtract(67*i,0);
                this.pieceNumber2++;
            }
        }
    }
}