package Game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class StartClass extends Application {
    static final int WIDTH = 960;
    static final int HEIGHT = 780;
    private static Stage primaryStage;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    private void printNumbers() {
        Set<Integer> s = new HashSet<>();

        int[][] world = MapLoader.getWorld();
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 100; j++)
                if (i < 21 && i > 9 && j > 43 && j < 54)
                    s.add(world[i][j]);
        }
        List<Integer> sortedList = new ArrayList<>(s);
        Collections.sort(sortedList);
        for (int a :
                sortedList) {
            System.out.print(a + ",");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MapLoader();
//        printNumbers();
        Pane root = FXMLLoader.load(Main.class.getResource("FXML/fxml_start_layout.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Age Of Empire");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
        StartClass.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
