package Game;

import Server.ServerListener;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private boolean started = false;
    private List<Player> players;
    private File mapFile;
    private boolean roamSoldier = true;
    private Person person;
    private Graphic graphic;
    private ServerListener serverListener;

    Game() {
        players = new ArrayList<>();
        person = new Person();
        graphic = new Graphic(new MapLoader().drawWorld());
    }

    /*EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
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
    };*/

    void addPlayer(Player player) {
        this.players.add(player);
    }

    public Player getThisPlayer() {
        return players.get(0);
    }

    public Player getPlayer(int id) {
        for (Player p :
                players)
            if (p.getID() == id)
                return p;
        return null;
    }

    public Graphic getGraphic() {
        return graphic;
    }


    public ServerListener getServerListener() {
        return serverListener;
    }

    public void setServerListener(ServerListener serverListener) {
        serverListener.start();
        this.serverListener = serverListener;
    }

    public void startGame() {
        started = true;
    }

    public boolean isGameStarted() {
        return started;
    }
}
