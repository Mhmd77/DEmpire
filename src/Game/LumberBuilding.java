package Game;

import javafx.scene.image.ImageView;

public class LumberBuilding extends Building {
    public LumberBuilding(int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, team, x, y, kind, imageView);
    }

    public LumberBuilding(int buildingId, int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(buildingId, life, team, x, y, kind, imageView);
    }

    @Override
    protected void collect(Person person) {
        setPerson(person);
        Main.getGame().getResources().get(Resource.WOOD).gainRatio();
    }

    @Override
    public void destroy() {

    }
}
