import java.util.ArrayList;

public interface Human {
    void setFoodAmount();

    void attack();

    void setSpeed();

    void setRadius();

    ArrayList<Tiles> roam(int i, int j, int igoal, int jgoal, Tiles[][] tiles);


    void setPower();

    void setClimbing();
}
