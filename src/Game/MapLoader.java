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

    public  List<Image> getImages() {
        return images;
    }

    private  List<Image> images;
    public static List<TileImageView> tileImages;
    private static final int TILE_SIZE = 16;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 60;

    MapLoader() {
        world = new int[HEIGHT][WIDTH];
        images = new ArrayList<>();
        tileImages=new ArrayList<>();
        for (int i = 1; i <= 1024; i++) {
            String imageFileName = "Images/world_image_";
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
        try (BufferedReader br = new BufferedReader(new FileReader("GameWorld.csv"))) {
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
                tileImages.add(img);
           //     img.mouseClicked();
            }
        return pane;
    }

    static boolean isFree(int i, int j) {
        if (Main.getGame().getGraphic().getDragImage() instanceof HarborImageView) {
            for (int m = 0; m < 6; m++)
                if (world[i][j] == m + 598 || world[i][j] == m + 758)
                    return true;

            return world[i][j] == 630 || world[i][j] == 662 || world[i][j] == 694 || world[i][j] == 726;
        }
        return world[i][j] == 0 || world[i][j] == 1 || world[i][j] == 32 || world[i][j] == 33;
    }
}
