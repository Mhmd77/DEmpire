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
                    int id = Integer.parseInt(command.substring(0, 1));
                    String[] values = command.substring(2).split("-");
                    processCommand(values, id);
                    sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void processCommand(String[] values, int id) {
        if (values[0].equals("building")) {
            int i = Integer.parseInt(values[1]);
            int j = Integer.parseInt(values[2]);
            int kind = Integer.parseInt(values[3]);
            Platform.runLater(() -> Main.getGame().getGraphic().createBuilding(i, j, kind));
        } else
            System.out.println(values[0] + values[1]);
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
