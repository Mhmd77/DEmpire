package Game;

import javafx.scene.image.ImageView;

/**
 * Created by asus on 7/5/2017.
 */
public class MineBuilding extends Building {
    MineBuilding(int life, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, x, y, kind, imageView);
    }
    @Override
    protected void collect() {
        super.collect();
        setBusy(true);
        System.out.println("COLLECTING");
        Main.getGame().getResources().get(Resource.GOLD).setRatio(1.5);
    }
}
