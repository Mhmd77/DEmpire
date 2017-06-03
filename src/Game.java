import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.Serializable;

class Game implements Serializable {
    private int[][] world;

    Game(File map) {
        world = new int[16][16];
        loadMap(map);
    }

    private void loadMap(File file) {
        for (int j = 0; j < 16; j++)
            world[0][j] = 5;

        for (int i = 1; i < 16; i++)
            for (int j = 0; j < 16; j++)
                world[i][j] = 0;
        for (int j = 0; j < 16; j++)
            world[16 - 1][j] = 1;
    }

    public int[][] getWorld() {
        return world;
    }
}
