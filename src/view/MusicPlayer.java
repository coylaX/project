package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public class MusicPlayer {
    public void playMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                //if (clip.isRunning()) clip.stop();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

