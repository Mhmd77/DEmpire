package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Building> buildings;
    private Resource[] resources;
    private int id;

    Player(int id) {
        buildings = new ArrayList<>();
        resources = new Resource[3];
        this.id = id;
    }


    public int getID() {
        return id;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

}
