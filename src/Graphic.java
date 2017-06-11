import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by ASUS on 6/7/2017.
 */
public class Graphic implements Graphics {

    private Group root;
    private Scene scene;
    private Canvas canvas;
    private Stage primaryStage;
    private GraphicsContext gc;
    private MapLoader mapLoader;
    private static final int TILE_SIZE = 16;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    Graphic(Group root, Scene scene, Canvas canvas, Stage primaryStage) {
        this.canvas = canvas;
        this.root = root;
        this.scene = scene;
        this.primaryStage = primaryStage;
        this.mapLoader = new MapLoader();
        mapLoader.loadMap();
    }

    void setGraphics() {
        primaryStage.setTitle("Age of Empire");
        primaryStage.setScene(scene);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        drawBackground();
        primaryStage.show();
    }

    @Override
    public void drawBackground() {
        mapLoader.drawWorld(gc);
    }

}
