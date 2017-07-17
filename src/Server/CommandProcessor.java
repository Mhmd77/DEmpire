package Server;

import Game.Building;
import Game.Main;
import Game.Person;
import javafx.application.Platform;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class CommandProcessor extends Thread {
    private final Queue<String> commands;

    CommandProcessor() {
        commands = new ArrayDeque<>();
    }

    @Override
    public void run() {
        synchronized (commands) {
            try {
                while (true) {
                    if (commands.isEmpty())
                        commands.wait();
                    String command = commands.poll();
                    String[] values = command.split("-");
                    int id = Integer.parseInt(values[0]);
                    processCommand(values, id);
                    sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void processCommand(String[] values, int id) {
        switch (values[1]) {
            case "building": {
                int i = Integer.parseInt(values[3]);
                int j = Integer.parseInt(values[4]);
                int kind = Integer.parseInt(values[5]);
                Platform.runLater(() -> Main.getGame().getGraphic().createBuilding(id, Integer.parseInt(values[2]), i, j, kind));
                break;
            }
            case "person_move": {
                int i = Integer.parseInt(values[2]);
                int j = Integer.parseInt(values[3]);
                int personID = Integer.parseInt(values[4]);
                Platform.runLater(() -> Main.getGame().getPlayer(id).getPersonByID(personID).move(i, j, id));
                break;
            }
            case "person_stop":
                Person person = Main.getGame().getPlayer(id).getPersonByID(Integer.parseInt(values[2]));
                if (person != null) {
                    person.stopTransition();
                }
                break;
            case "create_person":
                Platform.runLater(() -> Main.getGame().getPlayer(id).createEnemyPerson(Integer.parseInt(values[2]), Integer.parseInt(values[5]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), 0));
                break;
            case "create_soldier":
                Platform.runLater(() -> Main.getGame().getPlayer(id).createEnemyPerson(Integer.parseInt(values[2]), 70, Integer.parseInt(values[3]), Integer.parseInt(values[4]), 1));
                break;
            case "change_climbing":
                Main.getGame().getPlayer(id).changeClimbing();
                break;
            case "attack_person": {
                Person attacker = Main.getGame().getPlayer(id).getPersonByID(Integer.parseInt(values[2]));
                Person defender = Main.getGame().getPlayer(Integer.parseInt(values[3])).getPersonByID(Integer.parseInt(values[4]));
                if (attacker != null && defender != null)
                    attacker.attack(defender);
                break;
            }
            case "attack_building": {
                Person attacker = Main.getGame().getPlayer(id).getPersonByID(Integer.parseInt(values[2]));
                Building defender = Main.getGame().getPlayer(Integer.parseInt(values[3])).getBuildingByID(Integer.parseInt(values[4]));
                if (attacker != null && defender != null)
                    attacker.attack(defender);
                break;
            }
        }

    }

    void addCommandList(String command) {
        synchronized (commands) {
            commands.add(command);
            commands.notifyAll();
        }
    }

    Iterator getCommandsIterator() {
        return commands.iterator();
    }
}
