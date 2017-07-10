package Game;

import javafx.application.Platform;

import java.util.*;

public class Player {
    private List<Building> buildings;
    private List<Person> persons;
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
        persons = new ArrayList<Person>();
        System.out.println("Dashagh");
        createPersons(2);
    }

    private void createPersons(int n) {

//        for (int i = 0; i < n; i++) {
        if (this.id == Main.getGame().getThisPlayer().id) {

            Person p = new Person(0, 10, this.id, "Images/romanSoldier.png");
//            Person p2 = new Person(0, 0, this.id, "Images/romanSoldier.png");
//            persons.add(p2);
            persons.add(p);
        }else
            System.out.println(this.id);
//        }
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
}
