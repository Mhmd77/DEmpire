package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Group root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        drawBackground();
        primaryStage.show();
    }

    private void drawBackground() {
        Image grass = new Image("Images/grass.jpg");
        Image water = new Image("Images/water.jpg");
        Image rock = new Image("Images/rock.jpg");
        for (int j = 0; j < 8; j++)
            gc.drawImage(rock, j * 100, 0);
        for (int i = 1; i < 7; i++)
            for (int j = 0; j < 8; j++)
                gc.drawImage(grass, j * 100, i * 100);
        for (int j = 0; j < 8; j++)
            gc.drawImage(water, j * 100, 700);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
