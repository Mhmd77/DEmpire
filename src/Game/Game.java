package Game;

import Server.ServerListener;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.*;

public class Game {
    private boolean started = false;
    private List<Player> players;
    private File mapFile;
    private boolean roamSoldier = true;
    private Person person;
    private Graphic graphic;
    private ServerListener serverListener;
    private List<Resource> resources;


    Game() {

        players = new ArrayList<>();
       // person = new Person();
        graphic = new Graphic(new MapLoader().drawWorld());
        resources = new ArrayList<>();
        resources.add(new Resource(0));
        resources.add(new Resource(1));
        resources.add(new Resource(2));
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
    List<Resource> getResources() {
        return resources;
    }

    void addPlayer(Player player) {
        this.players.add(player);
    }

    Player getThisPlayer() {
        return players.get(0);
    }

    Player getPlayer(int id) {
        for (Player p :
                players)
            if (p.getID() == id)
                return p;
        return null;
    }

    public Graphic getGraphic() {
        return graphic;
    }


    ServerListener getServerListener() {
        return serverListener;
    }

    void setServerListener(ServerListener serverListener) {
        serverListener.start();
        this.serverListener = serverListener;
    }

    void startGame() {
        started = true;
        System.out.printf("Players ");
        for (Player p :
                players)
            System.out.printf(p.getID() + ",");
        System.out.println("Registered");
        collectResource();
    }

    void collectResource() {
        Timer timer = new java.util.Timer();
        for (Resource R :
                resources) {
            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            R.addValue(100);
                        }
                    });
                }
            }, 0, R.getTime());
        }
    }

    public boolean isGameStarted() {
        return started;
    }


}
