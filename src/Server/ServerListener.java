package Server;

import Game.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerListener extends Thread {
    private Socket socket;
    private CommandProcessor commandProcessor;
    public ServerListener(Socket socket) {
        this.socket = socket;
        commandProcessor = new CommandProcessor();
    }

    @Override
    public void run() {
        commandProcessor.start();
        Iterator it = commandProcessor.getCommandsIterator();
        while (it.hasNext()) {
            sendCommand((String) it.next());
        }
        while (true) {
            try {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String command = inputStream.readUTF();
                commandProcessor.addCommandLis(command);
                System.out.println(command);
            } catch (IOException e) {
                try {
                    sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void sendCommand(String op, int... values) {

        StringBuilder str = new StringBuilder();
        str.append(values[0]).append("-");
        str.append(op);
        for (int i = 1; i < values.length; i++)
            str.append("-").append(values[i]);
        if (Main.getGame().isGameStarted()) {
            DataOutputStream dataOutputStream = null;
            try {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(str.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            commandProcessor.addCommandLis(str.toString());
        }
    }

    private void sendCommand(String msg) {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
