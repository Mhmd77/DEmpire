import java.util.ArrayList;

/**
 * Created by ASUS on 6/7/2017.
 */
public class Player {
    private ArrayList<Building> buildings;
    private Resource[] resources;
    private int id;
    //ArrayList<Human> humen;
    Player(int id) {
        buildings = new ArrayList<>();
        resources = new Resource[3];
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
