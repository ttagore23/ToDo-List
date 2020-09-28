package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class PlaySound {

    private static final String HIT = "data/CantinaBand3.wav";

    // Partially referenced from StackOverFlow
    // https://stackoverflow.com/questions/27533617/play-sound-on-button-click
    public static void playAudio(String soundName) {
        try {
            URL file = (new File(soundName)).toURI().toURL();
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void completeSound() {
        playAudio(HIT);
    }
}
