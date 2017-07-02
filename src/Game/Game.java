package Game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;

public class Game {
    private int[][] world = new int[16][16];
    private ArrayList<Player> players;
    private File mapFile;
    private boolean roamSoldier = true;
    private Person person;
    private Graphic graphic;

    Game() {
        players = new ArrayList<>();
        person = new Person();
        graphic = new Graphic(new MapLoader().drawWorld());
    }


    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED) && (mouseEvent.getY() < 100) && (roamSoldier) && (
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

    void addPlayer(Player player) {
        this.players.add(player);
    }

    Player getThisPlayer() {
        return players.get(0);
    }

    public Graphic getGraphic() {
        return graphic;
    }
}
