package game.game_objects.bot.tornado;

import com.sun.source.tree.UsesTree;
import game.Settings;
import game.game_objects.GameObject;

public class TornadoSummoner extends GameObject {
    public TornadoSummoner(){

    }
    public void initTorrnado(){
        System.out.println("hjh");
        Tornado tornado1 = new Tornado();
        tornado1.position.set(Settings.PLAYGROUND_LEFT + 100 ,(Settings.PLAYGROUND_BOT + Settings.PLAYGROUND_TOP)/2);
        Tornado tornado2 = new Tornado();
        tornado2.position.set(Settings.PLAYGROUND_RIGHT - 100,(Settings.PLAYGROUND_BOT + Settings.PLAYGROUND_TOP)/2);
    }
}
