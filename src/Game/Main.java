package Game;

import ImageViews.BuildingImageView;
import ImageViews.HarborImageView;
import ImageViews.LumberImageView;
import ImageViews.MineImageView;
import Server.ServerListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    static final int WIDTH = 960;
    static final int HEIGHT = 780;
    private static Game game;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        VBox root = new VBox();
        primaryStage.setTitle("Age Of Empire");
        ScrollPane sp = game.getGraphic().createScrollPane();
        Thread thread = new Thread(game.getGraphic());

        root.getChildren().add(sp);
        root.getChildren().add(createBottomMenu());
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    Platform.runLater(() -> {

        try {
             thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    });
         //game.getGraphic().createPerson();

     //    thread.start();

        primaryStage.show();
    }

    /*private void printNumbers() {
        int[][] world = MapLoader.getWorld();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 30; j++)
                if (!(world[i][j] == 0 || world[i][j] == 1 || world[i][j] == 32 || world[i][j] == 33))
                    set.add(world[i][j]);
        }
        List<Integer> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        System.out.println(sortedList);
    }*/

    private TabPane createBottomMenu() throws IOException {
        TabPane root = FXMLLoader.load(Main.class.getResource("FXML/fxml_bottom_menu.fxml"));
        HBox hbox = (HBox) root.getTabs().get(0).getContent();
        addImages(hbox);
        AnchorPane pane = (AnchorPane) root.getTabs().get(1).getContent();
        Button b = new Button();
        b.setText("START");
        pane.getChildren().add(b);
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setDisable(true);
                try {

                    Socket socket = new Socket("localhost", 8888);
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    int thisID = inputStream.readInt();
                    System.out.println("Registered As " + thisID);
                    game.addPlayer(new Player(thisID));
                    while (true) {
                        try {
                            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                            boolean isFinished = dataInputStream.readBoolean();
                            if (isFinished)
                                break;
                            int id = dataInputStream.readInt();
                            System.out.println("Game.Player " + id + " Registered!");
                            game.addPlayer(new Player(id));
                        } catch (IOException e) {

                        }
                    }
                    game.startGame();
                    ServerListener serverListener = new ServerListener(socket);
                    game.setServerListener(serverListener);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        return root;
    }

    private void addImages(HBox hBox) {
        BuildingImageView src = new BuildingImageView("Images/castle.png");
        HarborImageView src2 = new HarborImageView("Images/harbor.png");
        MineImageView src3 = new MineImageView("Images/mine.png");
        LumberImageView src4 = new LumberImageView("Images/mine.png");
        hBox.getChildren().add(src);
        hBox.getChildren().add(src2);
        hBox.getChildren().add(src3);
        hBox.getChildren().add(src4);

    }

    public static Game getGame() {
        return game;
    }

}
