package Game;

import ImageViews.BuildingImageView;
import ImageViews.PersonImageView;
import ImageViews.TileImageView;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        personImage = new PersonImageView("Images/romanSoldier.jpg", 0, 0);
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
        PathFinder p = new PathFinder();
        ArrayList<Tiles> list = new ArrayList<Tiles>();
        list = p.roam(position[0], position[1], 55, 55);
        ImageView pImage = new ImageView();
        pImage.setImage(personImage.getImage());
        //final long startNanoTime = System.nanoTime();
        final int[] x = {position[0]};
        final int[] y = {position[1]};
        Main.getGame().getGraphic().add(pImage, y[0], x[0]);
        ArrayList<Tiles> finalList = list;
        System.out.println(finalList.size());
        new AnimationTimer() {
            int i = 0;

            public void handle(long currentNanoTime) {
                // double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                pImage.setX(finalList.get(i).i);
                pImage.setY(finalList.get(i).j);

                if (i == finalList.size() - 1)
                    stop();
                i++;

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
