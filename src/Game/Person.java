package Game;

import ImageViews.BuildingImageView;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;


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
        Tiles[] tiles = new Tiles[4];
        for (int i = 0; i < 4; i++) {
            tiles[i] = new Tiles();
        }
        tiles[0].i = 10;
        tiles[0].j = 10;
        tiles[1].i = 20;
        tiles[1].j = 50;
        tiles[2].i = 30;
        tiles[2].j = 50;
        tiles[3].i = 0;
        tiles[3].j = 0;
        boolean reachedDestination = false;
        ArrayList<Tiles> list = new ArrayList<Tiles>(Arrays.asList(tiles));
        ImageView pImage = new ImageView();
        pImage.setImage(personImage.getImage());
        final long startNanoTime = System.nanoTime();
        final int[] x = {1};
        final int[] y = {1};
        Image image = new Image("Images/romanSoldier.jpg");

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                //gc.drawImage(image,x[0],y[0]);
                pane.getChildren().remove(pImage);
                Main.getGame().getGraphic().add(pImage, y[0], x[0]);
                x[0] += 1;
                y[0] += 1;


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
