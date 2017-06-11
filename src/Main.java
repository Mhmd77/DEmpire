import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.scene.input.MouseEvent;

public class Main extends Application {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 800;
    private static ServerListener serverListener;
    private static Game game;
    public static void main(String[] args) throws IOException {
        game = new Game();
//        Socket socket = new Socket("localhost", 8888);
//        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//        int thisID = inputStream.readInt();
//        System.out.println("Registered As " + thisID);
//        game.addPlayer(new Player(thisID));
       /* while (true) {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                boolean isFinished = dataInputStream.readBoolean();
                if (isFinished)
                    break;
                int id = dataInputStream.readInt();
                System.out.println("Player " + id + " Registerd!");
                game.addPlayer(new Player(id));
            } catch (IOException e) {

            }
        }
        serverListener = new ServerListener(socket);
        serverListener.start();*/

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        ScrollPane sp = new ScrollPane(root);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene scene = new Scene(sp);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        OST ost = new OST();
        OST battle = new OST();
        OST menu = new OST();
        Graphic graphic = new Graphic(root, scene, canvas, primaryStage);
        ost.playSountrack();
        graphic.setGraphics();
        Button btn = new Button();
        btn.setText("CLICK");
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(sp.getVvalue());
                System.out.println(sp.getHvalue());
            }
        });
        root.getChildren().add(btn);
    }


}
