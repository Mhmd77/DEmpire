package Game;

import ImageViews.BuildingImageView;
import ImageViews.HarborImageView;
import ImageViews.LumberImageView;
import ImageViews.MineImageView;
import Server.ServerListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Main extends Application {
    static final int WIDTH = 960;
    static final int HEIGHT = 780;
    private static Game game;
    final static String BG_COLOR = "-fx-background-color: #dddddd";

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
   public void start(Stage primaryStage) throws Exception {
//        OST mainTheme=new OST("OTHER/Age-of-Empires-III-Soundtrack-Of-Licious_12620134.mp3");
//        mainTheme.playSountrack();
        game = new Game();
        VBox root = new VBox();
        root.setStyle(BG_COLOR);
        root.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Age Of Empire");
        createTopMenu(root);
        ScrollPane sp = game.getGraphic().createScrollPane();
        root.getChildren().add(sp);
        root.getChildren().add(createBottomMenu());
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.web("#dddddd"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        Person p=new Person(0,0);
        //  Platform.setImplicitExit(false);
        primaryStage.show();
    }

    private void createTopMenu(VBox root) {
        List<Resource> resources = game.getResources();
        HBox topMenu = new HBox();
        VBox R1 = new VBox(), R2 = new VBox(), R3 = new VBox();
        R1.setAlignment(Pos.CENTER);
        R2.setAlignment(Pos.CENTER);
        R3.setAlignment(Pos.CENTER);
        HBox.setMargin(R1, new Insets(0, 100, 0, 0));
        HBox.setMargin(R2, new Insets(0, 100, 0, 0));
        HBox.setMargin(R3, new Insets(0, 100, 0, 0));
        HBox.setHgrow(R1, Priority.ALWAYS);
        HBox.setHgrow(R2, Priority.ALWAYS);
        HBox.setHgrow(R3, Priority.ALWAYS);
        R1.getChildren().addAll(resources.get(0).getImage(), resources.get(0).getLabel());
        R2.getChildren().addAll(resources.get(1).getImage(), resources.get(1).getLabel());
        R3.getChildren().addAll(resources.get(2).getImage(), resources.get(2).getLabel());
        topMenu.getChildren().addAll(R1, R2, R3);
        root.getChildren().add(topMenu);
    }


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
                    for (int i = 0; i < thisID; i++)
                        game.addPlayer(new Player(i));
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
                            e.printStackTrace();
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
        Button b2 = new Button();
        b2.setText("Climbing");

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getGame().getThisPlayer().changeClimbing();
                if (getGame().getThisPlayer().isClimbing()) {
                    b2.setStyle("-fx-border-color: green;-fx-border-width: 2px");
                }else
                    b2.setStyle("-fx-border-color: crimson;-fx-border-width: 2px");
            }
        });
        b2.setLayoutX(50);
        pane.getChildren().add(b2);

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
