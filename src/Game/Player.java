package Game;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private List<Building> buildings;
    private int id;
    private boolean climbing;
    private double speed = 300;

    Player(int id) {
        buildings = new ArrayList<>();
        this.id = id;
        climbing = false;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public int getID() {
        return id;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }


    public boolean isClimbing() {
        return climbing;
    }

    public void changeClimbing() {
        this.climbing = !this.climbing;
        if (isClimbing())
            reduceSpeed();
        else gainSpeed();

    }

    void reduceSpeed() {
        speed /= 2;
    }
    void gainSpeed() {
        speed *= 2;
    }
}
