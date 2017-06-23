import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by ASUS on 6/7/2017.
 */
public class Graphic implements Graphics {

    private Group root;
    private Scene scene;
    private MapLoader mapLoader;
    GridPane pane;
    Graphic(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;
        this.mapLoader = new MapLoader();
//        mapLoader.loadMap();
    }

    void setGraphics(GridPane pane) {
        drawBackground();
    }

    @Override
    public void drawBackground() {
        mapLoader.drawWorld(pane);
    }

}
