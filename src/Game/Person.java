package Game;

import ImageViews.BuildingImageView;
import ImageViews.PersonImageView;
import ImageViews.TileImageView;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Person implements Human {
    List<TileImageView> tileImageView;


    private ArrayList<Tiles> closeList;
    private ArrayList<Tiles> openList;
    Integer[] position = new Integer[2];
    Integer[] destination = new Integer[2];
    int speed;
    int life;
    int foodAmount;
    int attackPower;
    boolean isClimbing;
    private PersonImageView personImage;

    Person() {

        tileImageView = MapLoader.tileImages;

        // destination[0]= MapLoader.tileImages.get(0).getI();
        //  dest = TileImageView.
        personImage = new PersonImageView("Images/romanSoldier.png", 0, 0);
        position[0] = personImage.getI();
        position[1] = personImage.getJ();

    }

    public void setDest() {
        for (int i = 0; i < 4800; i++) {
            TileImageView img = tileImageView.get(i);
            destination = img.mouseClicked();
            if ((destination[0] == null || destination[1] == null) && i == 4799)
                i = 0;

        }

    }


    @Override
    public void setFoodAmount() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void move(Pane pane) {

        ArrayList<Tiles> list = (new PathFinder()).roam(0, 0, 45, 70);
//        list = p.roam(position[0], position[1], 55, 55);
        ImageView pImage = new ImageView();
        pImage.setImage(personImage.getImage());
        //final long startNanoTime = System.nanoTime();
//        final int[] x = {position[0]};
//        final int[] y = {position[1]};

        PathFinder p = new PathFinder();
        PathTransition pathTransition = new PathTransition();
        Path path = new Path();
        path.getElements().add(new MoveTo(16, 16));
        for (Tiles t :
                list) {
            path.getElements().add(new LineTo(t.j * 16 + 16, t.i * 16 + 16));
        }

        pathTransition.setDuration(Duration.millis(300 * list.size()));
        pathTransition.setNode(pImage);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("FINISHED");
            }
        });
//        pathTransition.play();
//        pathTransition.
//        Main.getGame().getGraphic().add(pImage, 0, 0);
//        System.out.println(pImage.getLayoutX());
    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void setRadius() {

    }

    @Override
    public void setPower() {

    }

    @Override
    public void setClimbing() {

    }
}
