package Game;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private List<Building> buildings;
    private int id;

    Player(int id) {
        buildings = new ArrayList<>();
        this.id = id;

    }


    public int getID() {
        return id;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }



}
