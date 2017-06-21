import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public class Main extends Application {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 960;
    private static ServerListener serverListener;
    private static Game game;
    EventHandler<MouseEvent> mouseMove;
    private final double SCALE_DELTA = 1.05;

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
        VBox root = new VBox();
        primaryStage.setTitle("TilePane Experiment");
        GridPane grid = new GridPane();
        MapLoader mapLoader = new MapLoader();
        mapLoader.drawWorld(grid);
        ScrollPane sp = new ScrollPane(grid);
        grid.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
//                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor =
                        (event.getDeltaY() > 0)
                                ? SCALE_DELTA
                                : 1 / SCALE_DELTA;
                if(grid.getScaleX() < 0.65 && event.getDeltaY() < 0);
                else if(grid.getScaleX() > 1.1 && event.getDeltaY() > 0);
                else  {

                    grid.setScaleX(grid.getScaleX() * scaleFactor);
                    grid.setScaleY(grid.getScaleY() * scaleFactor);
                }
            }
        });
        grid.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    grid.setScaleX(1.0);
                    grid.setScaleY(1.0);
                }
            }
        });
        root.getChildren().add(sp);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
