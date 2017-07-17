package Game;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Player {
    private List<Building> buildings;
    private List<Person> persons;
    private int id;
    private boolean climbing;
    private double speedPerson = 300;
    private ExecutorService executor;

    public Player(int id) {
        buildings = new ArrayList<>();
        this.id = id;
        climbing = false;
        persons = new ArrayList<Person>();
        executor = Executors.newCachedThreadPool();
    }

    double getSpeedPerson() {
        return speedPerson;
    }

    void createFirstPersons() {
        for (int j = 0, s = 0; s < 10; j++, s++) {
            if (j < 0 || j > 59) {
                s--;
                continue;
            }
            Tiles pos = getCastle().getPos();
            Person p = new Person(persons.size(), pos.j + j, this.id, pos.i + 5);
            persons.add(p);
        }
    }

    void createPersonByArmy(int kind) {
        ArmyBuilding army = getArmy();
        if (army != null) {
            Tiles pos = army.getPos();
            Person person;
            if (kind == 0) {
                person = new Person(persons.size(), pos.j + 5, this.id, pos.i + 5);
                Main.getGame().getResources().get(Resource.FOOD).reduceValue(1000);
            } else {
                person = new Soldier(persons.size(), pos.j + 5, this.id, pos.i + 5);
                Main.getGame().getResources().get(Resource.FOOD).reduceValue(2000);
                Main.getGame().getResources().get(Resource.GOLD).reduceValue(250);
                Main.getGame().getResources().get(Resource.WOOD).reduceValue(600);

            }
            persons.add(person);
        }
    }

    public void createEnemyPerson(int personId, int attackPower, int j, int i, int kind) {
        Person person;
        if (kind == 0)
            person = new Person(personId, j, this.id, i, attackPower);
        else
            person = new Soldier(personId, j, this.id, i);

        persons.add(person);
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


    private CastleBuilding getCastle() {
        for (Building building : buildings) {
            if (building instanceof CastleBuilding) {
                return (CastleBuilding) building;
            }
        }
        return null;
    }

    private ArmyBuilding getArmy() {
        for (Building b : buildings)
            if (b instanceof ArmyBuilding) return (ArmyBuilding) b;
        return null;
    }

    public Person getPersonByID(int id) {
        for (Person person :
                persons)
            if (person.getPersonID() == id)
                return person;
        return null;
    }

    public Building getBuildingByID(int id) {
        for (Building building :
                buildings)
            if (building.getBuildingId() == id)
                return building;
        return null;
    }

    void removePerson(Person person) {
        persons.remove(person);
        Platform.runLater(() -> Main.getGame().getGraphic().removeNode(person.getPersonImage()));
    }

    void removeBuilding(Building building) {
        buildings.remove(building);
        building.destroy();
        Platform.runLater(() -> Main.getGame().getGraphic().removeNode(building.getImage()));
    }

    List<Person> getPersons() {
        return persons;
    }

    void addAttackListener(AttackListener attackListener) {
        executor.execute(attackListener);
    }

    List<Building> getBuildings() {
        return buildings;
    }

    int getAmountPersons() {
        int amount = 0;
        for (Person p :
                persons)
            if (p instanceof Soldier) amount += 2;
        amount += persons.size() - amount / 2;
        amount *= 10;
        return amount;
    }
}
