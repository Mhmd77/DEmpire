package Game;

import ImageViews.HarborImageView;
import ImageViews.TileImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private static int[][] world;

    private  List<Image> images;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 80;

    MapLoader() {
        world = new int[HEIGHT][WIDTH];
        images = new ArrayList<>();
        for (int i = 1; i <= 1024; i++) {
            String imageFileName = "Images/wolrd_image_";
            imageFileName += i + ".png";
            Image image = new Image(imageFileName);
            images.add(image);
        }
        loadMap();
    }

    public static int[][] getWorld() {
        return world;
    }

    private void loadMap() {
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader("newGameWorld.csv"))) {
            for (int i = 0; (line = br.readLine()) != null; i++) {
                String[] cells = line.split(",");
                for (int j = 0; j < cells.length; j++)
                    world[i][j] = Integer.valueOf(cells[j]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Pane drawWorld() {
        Pane pane = new Pane();
        pane.setPrefWidth(1280);
        pane.setPrefHeight(960);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                TileImageView img = new TileImageView(images.get(world[i][j]), i, j);
                img.setLayoutX(16 * j);
                img.setLayoutY(16 * i);
                pane.getChildren().add(img);
           //     img.setMouseClickListener();
            }
        return pane;
    }
}
