package Game;

import javafx.scene.image.ImageView;

public class FarmBuilding extends Building {

    FarmBuilding(int life, int team, int j, int i, BuildingKind kind, ImageView imageView) {
        super(life, team, j, i, kind, imageView);
    }

    FarmBuilding(int buildingId, int life, int team, int j, int i, BuildingKind kind, ImageView imageView) {
        super(buildingId, life, team, j, i, kind, imageView);
    }

    @Override
    protected void collect(Person person) {
        setPerson(person);
        Main.getGame().getResources().get(Resource.FOOD).gainRatio();
    }

    @Override
    public void destroy() {
        Main.getGame().getResources().get(Resource.FOOD).reduceRatio();
        getPerson().getAttackListener().cancel();
        Main.getGame().killPerson(getPerson());
    }
}
