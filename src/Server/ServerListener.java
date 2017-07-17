package Server;

import Game.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

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
                commandProcessor.addCommandList(inputStream.readUTF());
            } catch (IOException e) {
                try {
                    sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public synchronized void sendCommand(String op, int... values) {

        StringBuilder str = new StringBuilder();
        str.append(values[0]).append("-");
        str.append(op);
        for (int i = 1; i < values.length; i++)
            str.append("-").append(values[i]);
        if (Main.getGame().isGameStarted()) {
            DataOutputStream dataOutputStream;
            try {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(str.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            commandProcessor.addCommandList(str.toString());
        }
    }

    private void sendCommand(String msg) {
        DataOutputStream dataOutputStream;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
