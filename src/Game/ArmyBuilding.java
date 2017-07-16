package Game;

import javafx.scene.image.ImageView;

public class ArmyBuilding extends Building {
    public ArmyBuilding(int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, team, x, y, kind, imageView);
    }

    public ArmyBuilding(int buildingId, int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(buildingId, life, team, x, y, kind, imageView);
    }

    @Override
    public void destroy() {

    }

    @Override
    protected void collect(Person person) {

    }

}
