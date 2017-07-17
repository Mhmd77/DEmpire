package Game;

import javafx.scene.image.ImageView;

public class HarborBuilding extends Building {
    private ImageView ship;

    HarborBuilding(int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(life, team, x, y, kind, imageView);
        createShip();
    }

    HarborBuilding(int buildingId, int life, int team, int x, int y, BuildingKind kind, ImageView imageView) {
        super(buildingId, life, team, x, y, kind, imageView);
        createShip();
    }

    private void createShip() {
        ship = new ImageView("Images/ship.png");
        Tiles pos = getShipPos();
        if (pos != null) {
            Main.getGame().getGraphic().add(ship, pos.j, pos.i);
        } else
            System.out.println("No Place Found");

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

    private Tiles getShipPos() {
        Tiles t = getPos();
        int i = getPos().i, j = getPos().j;
        if (isWater(i, j - 1)) {
            t.j--;
            return t;
        } else if (isWater(i - 1, j - 1)) {
            t.j--;
            t.i--;
            return t;
        } else if (i < 79 && isWater(i + 1, j - 1)) {
            t.j--;
            t.i++;
            return t;
        } else if (isWater(i, j + 4)) {
            t.j += 4;
            return t;
        } else if (isWater(i - 1, j + 4)) {
            t.j += 4;
            t.i--;
            return t;
        } else if (i < 79 && isWater(i + 1, j + 4)) {
            t.j += 4;
            t.i++;
            return t;
        } else
            return null;
    }

    private boolean isWater(int i, int j) {

        int[] nFreeWater = {631, 632, 633, 634, 663, 664, 665, 666, 695, 696, 697, 698, 726, 727, 728, 729};
        int[][] world = MapLoader.getWorld();
        for (int x :
                nFreeWater)
            if (world[i][j] == x)
                return true;
        return false;
    }
}
