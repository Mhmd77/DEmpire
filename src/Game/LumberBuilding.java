package Game;

import javafx.scene.image.ImageView;

public class LumberBuilding extends Building {
    public LumberBuilding(int life, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, x, y, kind, imageView);
    }

    @Override
    protected void collect() {
        super.collect();
        setBusy(true);
        Main.getGame().getResources().get(Resource.WOOD).setRatio(1.5);
    }
}
