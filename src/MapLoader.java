import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private int[][] world;
    private List<Image> images;
    private static final int TILE_SIZE = 16;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    MapLoader() {
        world = new int[HEIGHT][WIDTH];
        images = new ArrayList<>();
        for (int i = 1; i <= 1024; i++) {
            String imageFileName = "Images/world_image_";
            imageFileName += i + ".png";
            Image image = new Image(imageFileName);
            images.add(image);
        }
    }

    public void loadMap() {
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader("worldmap1.csv"))) {
            for (int i = 0; (line = br.readLine()) != null; i++) {
                String[] cells = line.split(",");
                for (int j = 0; j < cells.length; j++)
                    world[i][j] = Integer.valueOf(cells[j]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void drawWorld(GraphicsContext graphicsContext) {
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                graphicsContext.drawImage(images.get(world[i][j]), j * TILE_SIZE, i * TILE_SIZE);
            }
    }
}
