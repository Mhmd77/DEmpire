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
    private Graphic graphic;
    private ServerListener serverListener;
    private List<Resource> resources;


    Game() {
        players = new ArrayList<>();
        graphic = new Graphic(new MapLoader().drawWorld());
        resources = new ArrayList<>();
        resources.add(new Resource(0));
        resources.add(new Resource(1));
        resources.add(new Resource(2));
    }

    List<Resource> getResources() {
        return resources;
    }

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
                players) {
            System.out.printf(p.getID() + ",");
//            p.createPersons(2);
        }
        System.out.println("Registered");
        collectResource();
    }

    private void collectResource() {
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
