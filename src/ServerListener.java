import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerListener extends Thread {
    private Socket socket;
    private List<String> commands;

    ServerListener(Socket socket) {
        this.socket = socket;
        commands = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                commands.add(inputStream.readUTF());
                System.out.println("Command:" + commands.get(commands.size() - 1));
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
        str.append(values[0]);
        str.append(op);
        for (int i = 1; i < values.length; i++)
            str.append(values[i]);
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
