package Game;

import java.util.ArrayList;

public interface Human {
    void setFoodAmount();

    void attack();

    void setSpeed();

    void setRadius();

    ArrayList<Tile> roam(int i, int j, int igoal, int jgoal, Tile[][] tiles);


    void setPower();

    void setClimbing();
}
