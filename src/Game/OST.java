package Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by ASUS on 6/7/2017.
 */
public class OST {
     String fileName;
    OST(String fileName){
        this.fileName=fileName;
    }

    public void playSountrack() {

//        String musicFile = fileName;
//        Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
      String  path = fileName;
       Media media = new Media(new File(path).toURI().toString());
      MediaPlayer  mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);



    }
}
