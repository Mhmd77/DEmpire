package Server;

import Game.Main;
import javafx.application.Platform;

import java.util.*;

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
        if (values[1].equals("building")) {
            int i = Integer.parseInt(values[2]);
            int j = Integer.parseInt(values[3]);
            int kind = Integer.parseInt(values[4]);
            Platform.runLater(() -> Main.getGame().getGraphic().createBuilding(id, i, j, kind));
        }
    }

    void addCommandLis(String command) {
        synchronized (commands) {
            commands.add(command);
            commands.notifyAll();
        }
    }

    Iterator getCommandsIterator() {
        return commands.iterator();
    }
}
