package game.game_objects.hole;

import game.Settings;
import game.game_objects.GameObject;

public class HoleSummoner extends GameObject {
    public HoleSummoner(){
    }

    public void initHoles(){
        Hole hole1 = GameObject.recycle(Hole.class);
        hole1.position.set(Settings.PLAYGROUND_LEFT,Settings.PLAYGROUND_TOP);
        Hole hole2 = GameObject.recycle(Hole.class);
        hole2.position.set(Settings.PLAYGROUND_RIGHT,Settings.PLAYGROUND_TOP);
        Hole hole3 = GameObject.recycle(Hole.class);
        hole3.position.set(Settings.PLAYGROUND_LEFT,Settings.PLAYGROUND_BOT);
        Hole hole4 = GameObject.recycle(Hole.class);
        hole4.position.set(Settings.PLAYGROUND_RIGHT,Settings.PLAYGROUND_BOT);
    }
}
