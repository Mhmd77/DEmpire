

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.Serializable;

class Game implements Serializable {
    private int[][] world;

    Person person = new Person();
    boolean roamSoldier = true;
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            if ((mouseEvent.getEventType().equals(mouseEvent.MOUSE_CLICKED)) && (person.position[0] < mouseEvent.getX()) && (person.position[0] + 50 > mouseEvent.getX())
                    && (person.position[1] < mouseEvent.getY()) && (person.position[1] + 50 > mouseEvent.getY()) && (roamSoldier)) {
                roamSoldier = false;
                return;
            }
            if ((mouseEvent.getEventType().equals(mouseEvent.MOUSE_CLICKED)) && (!roamSoldier)) {
                person.roam(person.position[0], person.position[1], (int) mouseEvent.getY(), (int) mouseEvent.getY(), person);
                roamSoldier = true;
            }
        }


    };


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
