import java.util.ArrayList;

/**
 * Created by ASUS on 6/7/2017.
 */
public class Player {
    ArrayList<Building> buildings;
    Resource[] resources;

    //ArrayList<Human> humen;
    Player() {
        buildings = new ArrayList<>();
        resources = new Resource[3];
    }
}
