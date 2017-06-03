import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Graphic {
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 800;
    private static final int WIDTH_TILE = 50;
    private static final int HEIGHT_TILE = 50;
    private static final int nTILE = 16;
    private static List<Image> images;

    public static void drawWorld(int[][] world, GraphicsContext gc) {
        images = new ArrayList<>();
        images.add(new Image("Images/grass.jpg"));//0
        images.add(new Image("Images/water.jpg"));//1
        images.add(new Image("Images/water2.jpg"));//2
        images.add(new Image("Images/water3.jpg"));//3
        images.add(new Image("Images/water4.jpg"));//4
        images.add(new Image("Images/rock.jpg"));//5
        images.add(new Image("Images/tree.jpg"));//6
        images.add(new Image("Images/mine.jpg"));//7
        for (int i = 0; i < nTILE; i++)
            for (int j = 0; j < nTILE; j++)
                gc.drawImage(images.get(world[i][j]), j * WIDTH_TILE, i * HEIGHT_TILE);

        /*gc.drawImage(tree, 5 * WIDTH_TILE, 10 * WIDTH_TILE);
        gc.drawImage(tree, 6 * WIDTH_TILE, 10 * WIDTH_TILE);
        gc.drawImage(tree, 5 * WIDTH_TILE, 11 * WIDTH_TILE);
        gc.drawImage(mine, 5 * WIDTH_TILE, 0 * WIDTH_TILE);
        gc.drawImage(mine, 4 * WIDTH_TILE, 0 * WIDTH_TILE);
        gc.drawImage(water3, (nTILE - 1) * WIDTH_TILE, (nTILE - 2) * WIDTH_TILE);
        gc.drawImage(water4, 0 * WIDTH_TILE, (nTILE - 2) * WIDTH_TILE);*/
    }
}
