import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Game {
    private int[][] world = new int[16][16];
    private ArrayList<Player> players;
    private File mapFile;
    private boolean roamSoldier = true;
    Person person = new Person();

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_CLICKED) && (mouseEvent.getY() < 100) && (roamSoldier) && (
                    person.position[0] < mouseEvent.getY()) && (person.position[0] + 50 > mouseEvent.getX())
                    && (person.position[1] < mouseEvent.getY()) && (person.position[1] + 50 > mouseEvent.getY())) {

                roamSoldier = false;
                return;
            }
            if (!roamSoldier) {
                person.roam(person.position[0], person.position[1], (int) mouseEvent.getX(), (int) mouseEvent.getY(), person);
                roamSoldier = true;
                return;
            }

        }
    };


    Game() {
        players = new ArrayList<>();
    }

    void addPlayer(Player player) {
        this.players.add(player);
    }

}
