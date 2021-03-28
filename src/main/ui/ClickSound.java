package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// Class that represents the sound played when a user clicks a button
// Citation: some of the code was obtained from SuaveSnippets
// URL: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
public class ClickSound {
    private static final String clickSound = "./data/soundclick.wav";

    // EFFECTS: plays a click sound from data file
    public void playClick() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(clickSound));
            Clip clickClip = AudioSystem.getClip();
            clickClip.open(ais);
            clickClip.start();
        } catch (Exception e) {
            System.out.println("Sound has failed to play!");
        }

    }
}
