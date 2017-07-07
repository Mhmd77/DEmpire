package Game;

import ImageViews.BuildingImageView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class Person implements Human {


    int[] position = new int[2];
    int speed;
    int life;
    int foodAmount;
    int attackPower;
    boolean isClimbing;
    private ImageView personImage;

    Person() {
        personImage = new ImageView("Images/romanSoldier.jpg");
    }

    @Override
    public void setFoodAmount() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void move(Pane pane) {
        ImageView pImage = new ImageView();
        pImage.setImage(personImage.getImage());
        final long startNanoTime = System.nanoTime();
        ArrayList<Tiles> tiles = (new PathFinder()).roam(55, 10, 55, 60);
        Main.getGame().getGraphic().add(pImage, 5, 5);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double x = pImage.getLayoutX();
                double y = pImage.getLayoutY();
                x += 160*t;
                y += 160*t;
                pImage.setLayoutX(x);
                pImage.setLayoutX(y);

            }
        }.start();

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
