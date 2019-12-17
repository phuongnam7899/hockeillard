package libs;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioUtils {
    public static Clip loadSound(String audioUrl) {
        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void replay(Clip sound) {
        sound.setFramePosition(0);
        sound.start();
    }
}
