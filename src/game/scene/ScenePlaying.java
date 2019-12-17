package game.scene;

import game.game_objects.amount_bar.PlateNumberPieceSummoner;
import game.game_objects.amount_bar.StaminaPieceSummoner;
import game.game_objects.background.BackgroundPlaying;
import game.game_objects.GameObject;
import game.game_objects.bot.hole_keeper.HoleKeeperSummoner;
import game.game_objects.bot.tornado.TornadoSummoner;
import game.game_objects.hole.HoleSummoner;
import game.game_objects.plate.Plate;
import game.game_objects.plate.PlateSummoner;
import game.game_objects.player.Player1;
import game.game_objects.player.Player2;

public class ScenePlaying extends Scene {
    @Override
    public void init() {
        GameObject.recycle(BackgroundPlaying.class);
        GameObject.recycle(Player1.class);
        GameObject.recycle(Player2.class);
        GameObject.recycle(Plate.class);
        GameObject.recycle(StaminaPieceSummoner.class);
        GameObject.recycle(PlateNumberPieceSummoner.class);
        HoleSummoner holeSummoner = GameObject.recycle(HoleSummoner.class);
        holeSummoner.initHoles();
        PlateSummoner plateSummoner = GameObject.recycle(PlateSummoner.class);
        plateSummoner.initPlate();
        HoleKeeperSummoner holeKeeperSummoner = GameObject.recycle(HoleKeeperSummoner.class);
        holeKeeperSummoner.initHoleKeeper();
        TornadoSummoner tornadoSummoner = GameObject.recycle(TornadoSummoner.class);
        tornadoSummoner.initTorrnado();

//        this.sound = AudioUtils.loadSound("assets/music/bgm/playing.wav");
//        this.sound.start();
    }

}
