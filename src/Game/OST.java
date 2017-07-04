package Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by ASUS on 6/7/2017.
 */
public class OST {

    public void playSountrack() {

        String musicFile = "OTHER/Age-of-Empires-III-Soundtrack-Of-Licious_12620134.mp3";
        Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
