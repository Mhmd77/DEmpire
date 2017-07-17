package Controllers;

import Game.Game;
import Game.Main;
import Game.Player;
import Game.StartClass;
import Server.ServerListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
public class StartController implements Initializable {
    @FXML
    public Button btnStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(event -> {
            try {
                Main main = new Main();
                Game game = Main.getGame();
                Socket socket = new Socket("192.168.100.85", 8888);
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                int thisID = inputStream.readInt();
                System.out.println("Registered As " + thisID);
                game.addPlayer(new Player(thisID));
                for (int i = 0; i < thisID; i++)
                    game.addPlayer(new Player(i));
                while (true) {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        boolean isFinished = dataInputStream.readBoolean();
                        if (isFinished)
                            break;
                        int id = dataInputStream.readInt();
                        game.addPlayer(new Player(id));
                    } catch (IOException ignored) {}
                }
                game.startGame();
                ServerListener serverListener = new ServerListener(socket);
                game.setServerListener(serverListener);
                main.startGame(StartClass.getPrimaryStage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
