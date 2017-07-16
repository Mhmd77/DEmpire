package Game;

import ImageViews.*;
import Server.ServerListener;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int WIDTH = 960;
    static final int HEIGHT = 780;
    private static Game game;
    final static String BG_COLOR = "-fx-background-color: #dddddd";

    public Main() {
        game = new Game();
    }

    public void startGame(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setStyle(BG_COLOR);
        root.setAlignment(Pos.CENTER);
        createTopMenu(root);
        ScrollPane sp = game.getGraphic().createScrollPane();
        root.getChildren().add(sp);
        root.getChildren().add(createBottomMenu());
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.web("#dddddd"));
        stage.setScene(scene);
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
        Button climbing = new Button();
        climbing.setText("Climbing");
        climbing.setOnAction(event -> {
            getGame().getThisPlayer().changeClimbing();
            getGame().getServerListener().sendCommand("change_climbing", getGame().getThisPlayer().getID());
            if (getGame().getThisPlayer().isClimbing()) {
                climbing.setStyle("-fx-background-color: #27ae60");
            } else
                climbing.setStyle("-fx-background-color: #e74c3c");
        });
        climbing.setLayoutX(50);
        pane.getChildren().add(climbing);
        return root;
    }

    private void addImages(HBox hBox) {
        BuildingImageView src = new BuildingImageView("Images/castle.png");
        HarborImageView src2 = new HarborImageView("Images/harbor.png");
        MineImageView src3 = new MineImageView("Images/mine.png");
        LumberImageView src4 = new LumberImageView("Images/mine.png");
        ArmyImageView src5 = new ArmyImageView("Images/army.png");
        hBox.getChildren().add(src);
        hBox.getChildren().add(src2);
        hBox.getChildren().add(src3);
        hBox.getChildren().add(src4);
        hBox.getChildren().add(src5);

    }

    public static Game getGame() {
        return game;
    }
}
