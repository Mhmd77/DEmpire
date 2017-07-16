package Game;

import javafx.scene.image.ImageView;

public class CastleBuilding extends Building {
    CastleBuilding(int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, team, x, y, kind, imageView);
        ;
    }

    CastleBuilding(int buildingId, int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(buildingId, life, team, x, y, kind, imageView);
    }

    @Override
    protected void collect(Person person) {
    }

    @Override
    public void destroy() {
        System.out.println("PLAYER " + Main.getGame().getThisPlayer().getID() + "LOST :(");
    }
}
