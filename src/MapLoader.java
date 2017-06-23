import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private int[][] world;
    private List<Image> images;
    private static final int TILE_SIZE = 16;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 60;

    MapLoader() {
        world = new int[HEIGHT][WIDTH];
        images = new ArrayList<>();
        for (int i = 1; i <= 1024; i++) {
            String imageFileName = "Images/world_image_";
            imageFileName += i + ".png";
            Image image = new Image(imageFileName);
            images.add(image);
        }
        loadMap();
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

    void drawWorld(GridPane pane) {
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                ImageView img = new ImageView(images.get(world[i][j]));
                pane.add(img, j, i);
            }
//        for (int i = 0; i < HEIGHT; i++)
//            for (int j = 0; j < WIDTH; j++) {
//                graphicsContext.drawImage(images.get(world[i][j]), j * TILE_SIZE, i * TILE_SIZE);
//            }
    }
}
