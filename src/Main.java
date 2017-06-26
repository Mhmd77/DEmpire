import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static final int WIDTH = 960;
    private static final int HEIGHT = 780;
    private static ServerListener serverListener;
    private static Game game;
    private static Graphic graphic;

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
        graphic = new Graphic(new MapLoader().drawWorld());
        ScrollPane sp = new ScrollPane(graphic.getGrid());
        //
        root.getChildren().add(sp);
        root.getChildren().add(createBottomMenu());
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TabPane createBottomMenu() throws IOException {
        TabPane root = FXMLLoader.load(getClass().getResource("FXML/fxml_bottom_menu.fxml"));
        HBox hbox = (HBox) root.getTabs().get(0).getContent();
        addImages(hbox);
        return root;
    }

    private void addImages(HBox hBox) {
        BuildingImageView src = new BuildingImageView("Images/castle.png");
        hBox.getChildren().add(src);
        //
    }

    public static Graphic getGraphic() {
        return graphic;
    }
}
