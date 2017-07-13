package Game;

import java.util.*;

public class Player {
    private List<Building> buildings;
    private List<Person> persons;
    private int id;
    private boolean climbing;
    private double speedPerson = 300;

    Player(int id) {
        buildings = new ArrayList<>();
        this.id = id;
        climbing = false;
        persons = new ArrayList<Person>();
    }

    public double getSpeedPerson() {
        return speedPerson;
    }

    void createPersons(int n) {
        Tiles pos = getCastle().getPos();
        Person p = new Person(pos.i + 5, pos.j + 5, this.id, "Images/romanSoldier.png");
        persons.add(p);
    }


    public int getID() {
        return id;
    }

    void addBuilding(Building building) {
        this.buildings.add(building);
    }


    boolean isClimbing() {
        return climbing;
    }

    public void changeClimbing() {
        this.climbing = !this.climbing;
        if (isClimbing())
            reduceSpeed();
        else gainSpeed();

    }

    private void reduceSpeed() {
        speedPerson *= 2;
    }

    private void gainSpeed() {
        speedPerson /= 2;
    }

    public void setPersons(Person persons) {
        this.persons.add(persons);
    }

    private CastleBuilding getCastle() {
        for (Building building : buildings) {
            if (building instanceof CastleBuilding) {
                return (CastleBuilding) building;
            }
        }
        System.out.println("null");
        return null;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
