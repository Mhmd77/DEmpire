package Game;

import java.util.ArrayList;

import Game.Person;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface Human {
    void setFoodAmount();

    void attack();

    void move(Pane pane);

    void setSpeed();

    void setRadius();

    void setPower();

    void setClimbing();
}
