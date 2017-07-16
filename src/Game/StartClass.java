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

    /*private void printNumbers() {
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

    }*/
    private List getFreeNumbers() {
        Integer[] b = {0, 1, 32, 33, 21, 30, 31, 53, 62, 63, 85, 94, 95, 117, 126, 127, 149, 158, 159, 190, 191, 214, 215, 216, 217,
                218, 219, 222, 223, 246, 247, 248, 249, 250, 251, 254, 255, 278, 279, 280, 281, 282, 283, 310, 311, 312, 313, 314, 315, 342,
                343, 344, 345, 346, 347, 374, 375, 376, 377, 378, 379, 590, 591, 622, 623
        };
        List<Integer> sortedList = new ArrayList<>(Arrays.asList(b));
        Collections.sort(sortedList);
        return sortedList;

    }

    private List<Integer> getRockNumbers() {
        Set<Integer> s = new HashSet<>();

        int[][] world = MapLoader.getWorld();
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 100; j++) {
                for (int m = 0; m < getFreeNumbers().size(); m++) {
                    if ((Integer) world[i][j] == getFreeNumbers().get(m))
                        break;
                    if (((i <= 12 && i >= 0 && j >= 0 && j <= 19) || (i <= 59 && i >= 37 && j >= 2 && j <= 19)
                            || (i >= 40 && i <= 59 && j <= 82 && j >= 70) || (
                            i >= 26 && i <= 50 && j >= 90 && j <= 100
                    ))
                            && (Integer) world[i][j] != getFreeNumbers().get(m)
                            && m != getFreeNumbers().size() - 1)
                        continue;

                    if (((i <= 12 && i >= 0 && j >= 0 && j <= 19) || (i <= 59 && i >= 37 && j >= 2 && j <= 19)
                            || (i >= 40 && i <= 59 && j <= 82 && j >= 70) || (
                            i >= 26 && i <= 50 && j >= 90 && j <= 100
                    ))
                            && (Integer) world[i][j] != getFreeNumbers().get(m)
                            && m == getFreeNumbers().size() - 1)
                        s.add(world[i][j]);
                }
            }
        }
        List<Integer> sortedList = new ArrayList<>(s);
        Collections.sort(sortedList);
        for (int a :
                sortedList) {
            System.out.print(a + ",");
        }
        return sortedList;

    }

    public List getWaterNumbers() {
        Set<Integer> s = new HashSet<>();
        List<Integer> rock = new ArrayList<>();
        List<Integer> free = new ArrayList<>();
        rock = getRockNumbers();
        free = getFreeNumbers();
        List<Integer> butWater = new ArrayList<>();
        butWater.addAll(rock);
        butWater.addAll(free);
        int[][] world = MapLoader.getWorld();
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 100; j++) {
                for (int m = 0; m < butWater.size(); m++) {
                    if ((Integer) world[i][j] == butWater.get(m))
                        break;

                    if (((i >= 0 && i <= 57 && j >= 34 && j <= 63) || (i >= 58 && i <= 80 && j <= 54 && j >= 43))
                            && (Integer) world[i][j] != butWater.get(m) && m != butWater.size() - 1)
                        continue;
                    if (((i >= 0 && i <= 57 && j >= 34 && j <= 63) || (i >= 58 && i <= 80 && j <= 54 && j >= 43))
                            && (Integer) world[i][j] != butWater.get(m) && m == butWater.size() - 1)
                        s.add(world[i][j]);

                }

            }
        }
        List<Integer> sortedList = new ArrayList<>(s);
        Collections.sort(sortedList);

        for (int a :
                sortedList) {
            System.out.print(a + ",");
        }
        return sortedList;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
//        new MapLoader();
//        getWaterNumbers();
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
