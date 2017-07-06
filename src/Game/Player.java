package Game;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private List<Building> buildings;
    private List<Resource> resources;
    private int id;

    Player(int id) {
        buildings = new ArrayList<>();
        this.id = id;
        resources = new ArrayList<>();
        resources.add(new Resource(0));
        resources.add(new Resource(1));
        resources.add(new Resource(2));
    }


    public int getID() {
        return id;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public List<Resource> getResources() {
        return resources;
    }

    void collectResource() {
        Timer timer = new java.util.Timer();
        for (Resource R :
                resources) {
            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            R.addValue(100);
                        }
                    });
                }
            }, 0, R.getTime());
        }

    }
}
