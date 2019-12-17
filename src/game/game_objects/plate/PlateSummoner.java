package game.game_objects.plate;

import game.Settings;
import game.game_objects.GameObject;

public class PlateSummoner extends GameObject {
    public PlateSummoner(){

    }
    public void  initPlate(){
        Plate plate1 = new Plate(1);
        plate1.position.set(650 - Settings.PLATE_RADIUS,300);
        Plate plate2 = new Plate(2);
        plate2.position.set(650 + Settings.PLATE_RADIUS,300);
        Plate plate3 = new Plate(1);
        plate3.position.set(650,300 - Settings.PLATE_RADIUS * (float)(Math.sqrt(3)));
        Plate plate4 = new Plate(2);
        plate4.position.set(650,300 + Settings.PLATE_RADIUS * (float)(Math.sqrt(3)));
        Plate plate5 = new Plate(1);
        plate5.position.set(450  + Settings.PLATE_RADIUS,300 + Settings.PLATE_RADIUS * (float)(Math.sqrt(3)));
        Plate plate6 = new Plate(2);
        plate6.position.set(850  - Settings.PLATE_RADIUS,300 + Settings.PLATE_RADIUS * (float)(Math.sqrt(3)));
    }
}
