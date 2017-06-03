import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Main extends Application {
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 800;
    private GraphicsContext gc;
    private Game game;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
//        Group root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Socket socket = null;
        Group root = new Group();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(WIDTH_SCREEN, HEIGHT_SCREEN);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        socket = new Socket("localhost", 8888);
        ObjectInputStream dataInputStream = new ObjectInputStream(socket.getInputStream());
        game = (Game) dataInputStream.readObject();
        Graphic.drawWorld(game.getWorld(), gc);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
