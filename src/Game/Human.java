package Game;

import java.util.ArrayList;

import Game.Person;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;

public interface Human {
    void setFoodAmount();

    void attack();

    void move(GridPane pane);

    void setSpeed();

    void setRadius();

    ArrayList<Tiles> roam(int i, int j, int igoal, int jgoal, Tiles[][] tiles);


    void setPower();

    void setClimbing();
}
