package Game;

import javafx.scene.image.ImageView;

public class MineBuilding extends Building {
    public MineBuilding(int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, team, x, y, kind, imageView);
    }

    MineBuilding(int buildingId, int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(buildingId, life, team, x, y, kind, imageView);
    }

    @Override
    protected void collect(Person person) {
        setPerson(person);
        Main.getGame().getResources().get(Resource.GOLD).gainRatio();
    }

    @Override
    public void destroy() {
        Main.getGame().getResources().get(Resource.GOLD).reduceRatio();
    }
}
