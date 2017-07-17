package Game;

import Server.ServerListener;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private boolean started = false;
    private List<Player> players;
    private Graphic graphic;
    private ServerListener serverListener;
    private List<Resource> resources;


    public Game() {
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

    public void addPlayer(Player player) {
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

    public void setServerListener(ServerListener serverListener) {
        serverListener.start();
        this.serverListener = serverListener;
    }

    public void startGame() {
        started = true;
        System.out.printf("Players ");
        for (Player p :
                players) {
            System.out.printf(p.getID() + ",");
//            p.createFirstPersons(2);
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
                    Platform.runLater(() -> {
                        R.addValue();
                        R.reduceValue(Main.getGame().getThisPlayer().getAmountPersons());
                    });
                }
            }, 0, R.getTime());
        }
    }

    public boolean isGameStarted() {
        return started;
    }

    List<Person> getEnemyPersons() {
        List<Person> enemies = new ArrayList<Person>();
        for (Player p :
                players)
            if (!p.equals(getThisPlayer())) {
                enemies.addAll(p.getPersons());
            }
        return enemies;
    }
    List<Building> getEnemyBuildings() {
        List<Building> buildings = new ArrayList<Building>();
        for (Player p :
                players)
            if (!p.equals(getThisPlayer()))
                buildings.addAll(p.getBuildings());
        return buildings;
    }
    public void killPerson(Person person) {
        for (Player p :
                players)
            if (p.getPersons().contains(person)) {
                p.removePerson(person);
                return;
            }
    }

    public void destroyBuilding(Building building) {
        for (Player p :
                players)
            if (p.getBuildings().contains(building)) {
                p.removeBuilding(building);
                return;
            }
    }


}