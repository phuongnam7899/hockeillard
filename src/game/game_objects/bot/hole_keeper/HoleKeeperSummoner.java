package game.game_objects.bot.hole_keeper;

import game.Settings;
import game.game_objects.GameObject;
import game.game_objects.bot.hole_keeper.HoleKeeper;

public class HoleKeeperSummoner extends  GameObject {
    public HoleKeeperSummoner(){

    }
    public void initHoleKeeper(){
        HoleKeeper holeKeeper1 = GameObject.recycle(HoleKeeper.class);
        holeKeeper1.position.set(Settings.PLAYGROUND_LEFT + 75,Settings.PLAYGROUND_TOP + 75);
        holeKeeper1.velocity.set(-1,1).setLength(2);
        HoleKeeper holeKeeper2 = GameObject.recycle(HoleKeeper.class);
        holeKeeper2.position.set(Settings.PLAYGROUND_RIGHT - 75,Settings.PLAYGROUND_TOP + 75);
        holeKeeper2.velocity.set(1,1).setLength(2);
        HoleKeeper holeKeeper3 = GameObject.recycle(HoleKeeper.class);
        holeKeeper3.position.set(Settings.PLAYGROUND_LEFT + 75,Settings.PLAYGROUND_BOT - 75);
        holeKeeper3.velocity.set(1,1).setLength(2);
        HoleKeeper holeKeeper4 = GameObject.recycle(HoleKeeper.class);
        holeKeeper4.position.set(Settings.PLAYGROUND_RIGHT - 75,Settings.PLAYGROUND_BOT - 75);
        holeKeeper4.velocity.set(-1,1).setLength(2);
    }
}
