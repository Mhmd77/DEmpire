package Game;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class CastleBuilding extends Building {

    CastleBuilding(int life, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, x, y, kind, imageView);
    }
}
