package Game;

import javafx.application.Platform;

import java.util.*;

public class Player {
    private List<Building> buildings;
    private List<Person> persons;
    private int id;
    private boolean climbing;
    private double speed = 300;
    private int[] personPosById;

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
        persons = new ArrayList<Person>();
    }

    public void createPersons(int n) {
        Tiles pos = getCastle().getPos();
        Person p = new Person(pos.i + 5, pos.j + 5, this.id, "Images/romanSoldier.png");
        persons.add(p);
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
        speed *= 2;}
    public void setPersons(Person persons) {
        this.persons.add(persons);
    }

    CastleBuilding getCastle() {
        for (Building building : buildings) {
            if (building instanceof CastleBuilding) {
                return (CastleBuilding) building;
            }
        }
        System.out.println("null");
        return null;
    }

}
