import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        OST ost = new OST();
        OST battle = new OST();
        OST menu = new OST();

        Graphic graphic = new Graphic(root, scene, canvas, primaryStage);
        ost.playSountrack();
        graphic.setGraphics();
    }


}
